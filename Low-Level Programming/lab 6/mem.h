#ifndef _MEM_H_
#define _MEM_H_

#define __USE_MISC

#define _DEFAULT_SOURCE

#include <stddef.h>
#include <stdint.h>
#include <stdio.h>

#include <sys/mman.h>

#define HEAP_START ((void*)0x04040000)
#define ALLOC_PERM_FLAGS PROT_READ     | PROT_WRITE
#define ALLOC_MAP_FLAGS  MAP_ANONYMOUS | MAP_PRIVATE 
#define ALLOC_MAP_KERNEL_FLAGS  MAP_ANONYMOUS | MAP_PRIVATE 

#pragma pack(push, 1)
typedef struct _mem_t {
	struct _mem_t* next;
	size_t capacity;
	int is_free;
} mem_t;
#pragma pack(pop)

void* s_malloc (size_t query);
void  s_free   (void*  mem);
void* heap_init(size_t initial_size);

#define DEBUG_FIRST_BYTES 4

void memalloc_debug_struct_info(FILE* f, const mem_t* const address);
void memalloc_debug_heap(FILE* f, const mem_t* ptr);

#endif
