#include <stdio.h>
#include <stdlib.h>
#define TYPE long  // or #define TYPE int
/*-----------
 *   list   |
 *          |
 *  front   |
 *   .^.    |
 *  back    |
 *---------------
 *              |
 *     node     |
 * last    next |
 *              |
 * --------------
 */

struct node {
	struct node *next;
	struct node *last;
	TYPE value;
};

struct list {
	struct node *front;
	struct node *back;
	int size;
};

struct node* nodeCreate(TYPE val){
	struct node *new_node = (struct node *)malloc(sizeof(struct node));
	new_node->value = val; 	
	return new_node;
}

struct list* createList(){
	struct list *new_list = (struct list *)malloc(sizeof(struct list));
	new_list->back = NULL;
	new_list->front = NULL;
	new_list->size = 0;
	return new_list;
}

struct node* defaultNode(){
	struct node *default_node = (struct node *)malloc(sizeof(struct node));
	default_node->last = NULL;
	default_node->next = NULL;
	default_node->value = -1; 	
	return default_node;
}

void listAddFront(struct list *l, TYPE val) {
	struct node *new_element = nodeCreate(val);
	if (l->back == NULL) {
		new_element->last = NULL;
		new_element->next = NULL;  
		l->back = new_element;
		l->size=1;
	} else {
		l->front->next = new_element;								
		new_element->last = l->front;    
		new_element->next = NULL; 
		l->size++;  
	}
	l->front = new_element;
}

void listAddBack(struct list *l, TYPE val){
	struct node *new_element = nodeCreate(val);
	if (l->back == NULL) {
		new_element->last = NULL;
		new_element->next = NULL;  
		l->front = new_element;
		l->size=1;
	} else {
		l->back->last = new_element;								
		new_element->next = l->back;    
		new_element->last = NULL; 
		l->size++;  
	}
	l->back = new_element;
}

int listLength(struct list *l){
	return l->size;
}

TYPE listGet(struct list *l, int index){	
	if(index>(l->size)){
		return defaultNode()->value;
	}
	struct node* node_result = l->back;
	while(index!=0){
		node_result = node_result->next;
		index--;
	}
	return node_result->value;
}

TYPE sum(struct list *l){
	struct node *element = l->back;
	TYPE sum=element->value;
	while(element->next != NULL){
		element = element->next;
		sum+=element->value;		
	}
	return sum;
}

void listFree(struct list *l){
	if((l->size) ==0){
		return;
	}
    struct node *element = l->back;
    struct node *next_element;
    while (element->next != NULL){   
		next_element = element->next;
//		printf("%d\n", element->value);
        free(element);
        element = next_element;
    }
    free(element);
//  printf("%d\n", element->value);
    free(l);
}

struct node* listNodeAt(struct list *l, int index){	
	if(index>(l->size)){
		return NULL;
	}
	struct node* node_result = l->back;
	while(index!=0){
		node_result = node_result->next;
		index--;
	}
	return node_result;
}

void fill(struct list *l){
	
    char string[512], *start, *end;
    TYPE value;
    end = string;
    fflush(stdin);
    printf("Enter values for list:\n");
    fgets(string, 512, stdin);
    
    
    while (*end != '\n' || *end != '\0'){
        while (*end == ' ' || *end == '\t'){
            end++;
        }
        start = end;
        value = strtol(start, &end, 10);
        if (start == end) break;        
        listAddFront(l,value);
    }
      
}

int function(int x){
	return x+2;
}

typedef int(fptr)(int);

int foreach(struct list *l, fptr func){
	
}

void map_mul(struct list *l, fptr func){
	struct node *element = l->back;
	element->value = func(element->value);
	while(element->next != NULL){
		element = element->next;
		element->value = func(element->value);
	}
}

struct list* map(struct list *l, fptr func){
	struct list *new_list = createList(); 
	struct node *element = l->back;
	listAddFront(new_list, func(element->value));
	while(element->next != NULL){
		element = element->next;
		listAddFront(new_list, func(element->value));
	}
	return new_list;
}

struct list* iterate(int s, int n, fptr func){
	struct list *new_list = createList(); 
	listAddFront(new_list, s);
	for(int i=0; i<n-1; i++){
		listAddFront(new_list, func(new_list->front->value));
	}
	return new_list;
}
 

int main(){
	struct list *my_list = createList(); 
	fill(my_list);	
	printf("%d\n", sum(my_list));
	printf("%d\n", listGet(my_list, 1));
	//struct list *my_list2 = map(my_list, function);
	//printf("%d\n", sum(my_list2));
	//struct list *my_list3 = iterate(3, 6, function);
	//printf("%d\n", sum(my_list3));
	printf("%d\n", sizeof(my_list->back->value));  // return syzeof(int) or syzeof(long)
	return 0;
}

