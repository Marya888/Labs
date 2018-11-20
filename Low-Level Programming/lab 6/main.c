#include <signal.h>
#include "mem.h"


int main(void) {
	mem_t* block = heap_init(1);
	mem_t* first;
	mem_t* first2;
	mem_t* first3;
	
    /* for 000
    first = s_malloc(2000 * sizeof(uint8_t)); 
    first2 = s_malloc(3000 * sizeof(uint8_t)); 
    first3 = s_malloc(500 * sizeof(uint8_t)); 
	  */
    /* for 001
    first = s_malloc(2000 * sizeof(uint8_t)); 
    first2 = s_malloc(9000 * sizeof(uint8_t)); 
    first3 = s_malloc(3000 * sizeof(uint8_t)); 
    s_free(first);
    first = s_malloc(500 * sizeof(uint8_t)); 
    s_free(first);
    //printf("lol=%p",block->next->capacity);
    //printf("lol=%p",first3->next);
    */
    //*for 010
    first = s_malloc(2000 * sizeof(uint8_t)); 
    first2 = s_malloc(9000 * sizeof(uint8_t)); 
    first3 = s_malloc(3000 * sizeof(uint8_t)); 
    /*/    
    /* for 011
    first = s_malloc(2000 * sizeof(uint8_t)); 
    first2 = s_malloc(9000 * sizeof(uint8_t)); 
    first3 = s_malloc(20000 * sizeof(uint8_t)); 
    */
    /* for 100
    first = s_malloc(9000 * sizeof(uint8_t)); 
    first2 = s_malloc(3000 * sizeof(uint8_t)); 
    first3 = s_malloc(500 * sizeof(uint8_t)); 
	  */
    /* for 101
    first = s_malloc(9000 * sizeof(uint8_t)); 
    first2 = s_malloc(3000 * sizeof(uint8_t)); 
    first3 = s_malloc(20000 * sizeof(uint8_t)); 
    */ 
    /* for 110
    first = s_malloc(9000 * sizeof(uint8_t)); 
    first2 = s_malloc(20000 * sizeof(uint8_t)); 
    first3 = s_malloc(3000 * sizeof(uint8_t)); 
    */  
    /* for 111
    first = s_malloc(9000 * sizeof(uint8_t)); 
    first2 = s_malloc(20000 * sizeof(uint8_t)); 
    first3 = s_malloc(50000 * sizeof(uint8_t)); 
    */
    /*   
	  printf("%p\n", (size_t)block);
    printf("%ld\n", block->capacity);
    printf("%d\n", block->is_free);
	
    printf("%p\n", block->next);
	  printf("%ld\n", (block->next)->capacity);
    printf("%d\n", (block->next)->is_free);
    
	  printf("%p\n", (block->next)->next);
	  printf("%ld\n", ((block->next)->next)->capacity);
    printf("%d\n", ((block->next)->next)->is_free);
    
    printf("%p\n", ((block->next)->next)->next);
	  printf("%ld\n", (((block->next)->next)->next)->capacity);
    printf("%d\n", (((block->next)->next)->next)->is_free);
    
    printf("%p\n", ((block->next)->next)->next->next);
	  printf("%ld\n", (((block->next)->next)->next)->next->capacity);
    printf("%d\n", (((block->next)->next)->next)->next->is_free);
    
    printf("%p\n", ((block->next)->next)->next->next->next);
    */
    printf("first1 = %p\n", first);
    printf("%ld\n", first->capacity);
    printf("%d\n", first->is_free);
    
    printf("first2 = %p\n", first2);
    printf("%ld\n", first2->capacity);
    printf("%d\n", first2->is_free);
    
    printf("first3 = %p\n", first3);
    printf("%ld\n", first3->capacity);
    printf("%d\n", first3->is_free);
	  return 0;
}
