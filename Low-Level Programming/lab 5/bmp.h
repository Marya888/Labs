#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

#pragma pack(push, 2)

typedef struct {
	uint16_t bfType; //Отметка для отличия формата от других (сигнатура формата). Может содержать единственное значение 4D42 in 16 form
 	uint32_t bfileSize; //размер в байтах
	uint32_t bfReserved; //зарезервировано в 0
	uint32_t bOffBits; // положение пиксельных данных относительно начала
	uint32_t biSize; //размер данной структуры в байтах
	uint32_t biWidth; //ширина растра в пикселях
	uint32_t biHeight; //высота 
	uint16_t biPlanes; //1
	uint16_t biBitCount; //количество бит на пиксель
	uint32_t biCompression; //способ хранения пикселей
	uint32_t biSizeImage; //размер пиксельных данных в байтах
	uint32_t biXPelsPerMeter; //количество пикселей на метр по горизонтале
	uint32_t biYPelsPerMeter;// по вертикале
	uint32_t biClrUsed; //размер таблицы цветов 
	uint32_t biClrImportant; //количество ячеек от начала таблицы цветов
} bmp_header_t;

#pragma pack(pop)

typedef struct pixel_t { char b, g, r; } pixel_t;
typedef struct image_t {
	uint32_t width, height;
	pixel_t* data;
} image_t;

typedef enum {
	READ_OK = 0,
	READ_INVALID_SIGNATURE,
	READ_INVALID_BITS,
	READ_INVALID_HEADER
} read_error_code_t;

typedef enum write_error_code_t {
	WRITE_OK = 0,
	WRITE_ERROR
} write_error_code_t;

extern read_error_code_t from_bmp(FILE* in, image_t* const read);
extern image_t rotate(image_t const source);
extern write_error_code_t to_bmp(FILE* out, image_t const* img);
extern void copy_header(FILE* in, FILE* out, int flag);
//extern pixel_t get_coordinate(int32_t i, int32_t j, image_t const source);
//extern pixel_t gauss_change(pixel_t array[3][3]);

