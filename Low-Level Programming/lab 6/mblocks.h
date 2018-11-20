#ifndef _LLIST__H_
#define _LLIST_H_

#include "mem.h"

mem_t* finding_the_right_block(mem_t* block, size_t query);
mem_t* block_befor(mem_t* block_start, mem_t* block);
mem_t* last_block(mem_t* block);
mem_t* last_block_on_queue();
mem_t* block_befor_delete_block(mem_t* block);
mem_t* mblocks_get(mem_t* list, int8_t* ptr);
mem_t* mblocks_get_prev(mem_t* list, mem_t* blk);

#endif
