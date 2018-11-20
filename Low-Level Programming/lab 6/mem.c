#include <signal.h>
#include "mem.h"
#include "mblocks.h"
#define PAGE_SIZE 4095 

static size_t mem_from_pages(const size_t n) { 
    return PAGE_SIZE*n;
}

static size_t pages_for(const size_t n) { 
    return (n / PAGE_SIZE + 1)*PAGE_SIZE;
}

static void* allocate(int8_t* ptr, size_t query) { 
    return mmap(ptr, pages_for(query), ALLOC_PERM_FLAGS, ALLOC_MAP_FLAGS, -1, 0);
}

static void* allocate_by_kernel(size_t query) { 
    return mmap(NULL, pages_for(query), ALLOC_PERM_FLAGS, ALLOC_MAP_KERNEL_FLAGS, -1, 0);
}

void* heap_init(size_t initial_size) {
	void* block = allocate(HEAP_START, mem_from_pages(initial_size));
    mem_t* head = HEAP_START;

	head->next = NULL;
	head->capacity = mem_from_pages(initial_size) - sizeof(mem_t);
	head->is_free = 1;
	return block;
}


static mem_t* create_new_block(mem_t* last, size_t query) {
	int8_t* alloc_address = (int8_t*)last + last->capacity + sizeof(mem_t); 
    size_t alloc_size = query + sizeof(mem_t);
    size_t allocated_memory = 0;
	mem_t* block;
    mem_t* unused  = NULL;
     
    if ((block = allocate(alloc_address, alloc_size)) == MAP_FAILED &&
        (block = allocate_by_kernel(alloc_size)) == MAP_FAILED) {
        return NULL;
    }
    allocated_memory = pages_for(alloc_size);

    if (alloc_size + sizeof(mem_t) < allocated_memory) {
        unused = (mem_t*)(alloc_address + alloc_size);    
        unused->capacity = allocated_memory - alloc_size - sizeof(mem_t);
        unused->is_free  = 1;
        unused->next     = NULL;
    }

	last->next = block;

    block->capacity = query;
    block->is_free  = 0;    
    block->next     = unused;
    return block;
}   

static mem_t* enlarge(mem_t* block, size_t query) {
    int8_t* alloc_start = (int8_t*)block + block->capacity + sizeof(mem_t);
    size_t  alloc_size  = query - block->capacity;
    size_t  alloc_mem   = 0;
    mem_t*  unused = NULL;

    if (query < block->capacity) { /* undefined situation for resizing */
        raise(SIGILL);
    }

    if (allocate(alloc_start, alloc_size) != MAP_FAILED) {
        alloc_mem = pages_for(alloc_size);
        if (alloc_size + sizeof(mem_t) < alloc_mem) {            
            unused = (mem_t*)(alloc_start + alloc_size);            
            unused->capacity = alloc_mem - alloc_size - sizeof(mem_t);
            unused->is_free  = 1;
            unused->next     = NULL;
        }

        block->capacity = query;
        block->next     = unused;
        return block;
    } else {
        return NULL;
    }    
}

void* s_malloc(size_t query) {
	mem_t* block = mblocks_look_up(HEAP_START, query);
	mem_t* new = NULL;
	if(block) {
        if (block->capacity == query || 
            (block->capacity - query - sizeof(mem_t)) > block->capacity) {
           /* if block is perfect for query or cannot be splited */
           block->is_free = 0;
           return (int8_t*)block + sizeof(mem_t); 
        } 
        /* else split block */
		new = (mem_t*)((uint8_t*)block + sizeof(mem_t) + query);

		new->capacity   = block->capacity - query - sizeof(mem_t);
		new->is_free    = 1;
		new->next       = block->next;
		block->next     = new;
		block->capacity = query; 
		block->is_free  = 0;
	} else {
		block = mblocks_last(HEAP_START);
        if (block->is_free && enlarge(block, query)) {
            block->is_free = 0;
        } else if ((new = create_new_block(block, query))) {
            block = new;                
            block->is_free = 0;
        } else {
            return NULL;
        }
	}
	return (int8_t*)block + sizeof(mem_t);
}

void s_free(void* mem) {
	mem_t* block = mblocks_get(HEAP_START, (int8_t*)mem);
	mem_t* prev  = mblocks_get_prev(HEAP_START, block);
	if (block) {
		block->is_free = 1;
		if (block->next) {
			if(block->next->is_free == 1) {
				block->capacity += block->next->capacity + sizeof(mem_t);
				block->next = block->next->next;
			}
		}
		if (prev) {
			if(prev->is_free == 1) {
				prev->capacity += block->capacity + sizeof(mem_t);
				prev->next = block->next;
			}
		}
	}
}

void memalloc_debug_struct_info(FILE* f, const mem_t* const address) {
	size_t i;
	fprintf(f, "start: %p\nsize: %lu\nis_free: %d\n", 
                (void*)address, 
                address->capacity,
                address->is_free);

	for ( i = 0; i < DEBUG_FIRST_BYTES && i < address->capacity; i++ ) {
		fprintf(f, "%X", ((uint8_t*)address)[sizeof(mem_t) + i]);
	    putc('\n', f);
    }
}

void memalloc_debug_heap(FILE* f, const mem_t* ptr) {
	for(;ptr; ptr = ptr->next ) {
		memalloc_debug_struct_info(f, ptr);
    }
}
