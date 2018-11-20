#include <signal.h>
#include "mem.h"
#include "mem.h"
#include "mblocks.h"
#define PAGE_SIZE 4095 

static void* fill(int8_t* addr, size_t query) { 
    return mmap(addr, query + sizeof(mem_t), ALLOC_PERM_FLAGS, ALLOC_MAP_FLAGS, -1, 0);
}

void* heap_init(size_t initial_size) {
	void* block = fill(HEAP_START, PAGE_SIZE * (initial_size + 1));
    mem_t* head = (size_t)block;
    mem_t* start = (size_t)block + sizeof(mem_t);
	
	head->next = start;
	head->capacity = sizeof(mem_t);
	head->is_free = 0;

	start->next = NULL;
	start->capacity = PAGE_SIZE * (initial_size + 1) - 2 * sizeof(mem_t);
	start->is_free = 1;
	
	return block;
}

void* s_malloc(size_t query) {
	mem_t* fit_block = finding_the_right_block(HEAP_START, query);
	mem_t* new = NULL;
	if(fit_block){	
		mem_t* previous_block = block_befor(HEAP_START, fit_block);
		//printf("previous_block=%p\n",previous_block);
		//printf("now=%p\n",fit_block);
		//printf("size=%d\n",previous_block->capacity);
		//...
		new = (mem_t*)((uint8_t*)((size_t)fit_block + previous_block->capacity));
		//printf("new=%p\n",new);
		
		if (fit_block->capacity == query + sizeof(mem_t)) {
			new->capacity = 0;
			new->is_free = 0;			
		}else{ 
			new->capacity = fit_block->capacity - sizeof(mem_t) - query;
			new->is_free = 1;
		}
		
		mem_t* old_end = last_block(HEAP_START);
		
		new->next = NULL;
		old_end->next = new;
			
	//	fit_block->next = new;
		fit_block->capacity = query + sizeof(mem_t);
		fit_block->is_free = 0;
	}else{
		mem_t* old_end = last_block(HEAP_START);
		mem_t* last_block_from_all_blocks = last_block_on_queue();
		
		int old_capacity = block_befor(HEAP_START, last_block_from_all_blocks)->capacity;
		void* new_block = fill((uint8_t*)last_block_from_all_blocks + last_block_from_all_blocks->capacity + old_capacity, 2 * query);
	//	printf("===%p\n", new_block);
		
		new = (mem_t*)new_block;
		
		new->next = NULL;//old_end->next;
		new->capacity = query;
		new->is_free = 1;
		
		old_end->next = new;
	}
	
	return (int8_t*)new; 
}

void s_free(void* mem){
	mem_t* block = mblocks_get(HEAP_START, (int8_t*)mem);
	mem_t* prev_block;
	if(block){
		mem_t* prev_block = mblocks_get_prev(HEAP_START, block);
		mem_t* block_befor_delete = block_befor_delete_block(block);
		
		//printf("aaaaaaa = %d", 
		//((int)(size_t)block_befor_delete+mblocks_get_prev(HEAP_START, block_befor_delete)->capacity) == (int)(size_t)block
		//);
	if(((int)(size_t)block_befor_delete+mblocks_get_prev(HEAP_START, block_befor_delete)->capacity) == (int)(size_t)block){
		if(block->is_free){
			block_befor_delete->capacity = block_befor_delete->capacity + block->capacity;
		}
		}
		
		//printf("========%p\n",block_befor_delete_block(block));
		//printf("prev_block = %p\n",prev_block);
		//printf("befor_pre_block->capacity = %d\n",prev_block->capacity);
		//printf("before_block->capacity = %d\n",block->capacity);
		if(block->next == NULL){
			prev_block->next = NULL;
		}else{
			prev_block->next = block->next;
		}
		
		prev_block->is_free = 1;
		
		//printf("pre_block->capacity = %d\n",prev_block->capacity);
		//printf("block->capacity = %d\n",block->capacity);
		//printf("pre_block->capacity = %d\n",prev_block->capacity);
	}
}

