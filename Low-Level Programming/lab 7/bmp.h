#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

#pragma pack(push, 2)
typedef struct {
	uint16_t bfType; 
	uint32_t bfileSize; 
	uint32_t bfReserved; 
	uint32_t bOffBits;
	uint32_t biSize;
	uint32_t biWidth; 
	uint32_t biHeight; 
	uint16_t biPlanes; 
	uint16_t biBitCount; 
	uint32_t biCompression;
	uint32_t biSizeImage;
	uint32_t biXPelsPerMeter; 
	uint32_t biYPelsPerMeter;
	uint32_t biClrUsed; 
	uint32_t biClrImportant; 
} bmp_header_t;
#pragma pack(pop)

typedef struct pixel_t {unsigned char b, g, r; } pixel_t;


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
