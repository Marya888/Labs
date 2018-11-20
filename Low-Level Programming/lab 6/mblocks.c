#include <stdio.h>
#include <stdlib.h>
#include "mblocks.h"

mem_t* finding_the_right_block(mem_t* block, size_t query) {
    size_t capacity;
	while(block != NULL) {
        capacity = block->capacity;
		if(capacity >= query && block->is_free == 1) {
		//	printf("stop=%p", block);
			return block;
		}
		block = block->next;
	}
	return NULL;
}

mem_t* block_befor(mem_t* block_start, mem_t* block){
	while(block_start->next != NULL) {
		if((size_t)block_start->next == (size_t)block){
			return block_start;
		}
		block_start = block_start->next;
	}
	return NULL;
}

mem_t* last_block(mem_t* block) {
	while(block->next != NULL) {
		block = block->next;
	}
	return block;
}

mem_t* last_block_on_queue() {
	mem_t* last_block = NULL;
	mem_t* block = HEAP_START;
	block = block->next;
	size_t max = 0;
	while(block != NULL) {
	//	printf("stop=%p    %ld\n", block, ((size_t)block - (size_t)HEAP_START));
		if((size_t)block - (size_t)HEAP_START > max){
			last_block = block;	
			max = (size_t)block - (size_t)HEAP_START;		
		}
		block = block->next;
	}
	//printf("last_block=%p\n", last_block);
	return last_block;
}

mem_t* block_befor_delete_block(mem_t* block){
	size_t min = 10000000000000;
	mem_t* block_p = NULL;
	mem_t* start_block = HEAP_START;
	while(start_block->next != NULL) {
		//printf("kek=%d\n",(int)((size_t)start_block - (size_t)block)<0);
		if((int)((size_t)start_block - (size_t)block) < 0){
			if((size_t)block - (size_t)start_block < min){
				block_p = start_block;
				min = (int)((size_t)start_block - (size_t)block);
			}
		}
		start_block = start_block->next;
	}
	return block_p;
}

mem_t* mblocks_get(mem_t* block, int8_t* ptr) {
	while(block != NULL) {
		if(block == (mem_t*)ptr) {
			return block;
		}
		block = block->next;
	}
	return NULL;
}

mem_t* mblocks_get_prev(mem_t* block, mem_t* blk) {
	while(block != NULL) {
		if(block->next == blk) {
			return block;
		}
		block = block->next;
	}
	return NULL;
}
