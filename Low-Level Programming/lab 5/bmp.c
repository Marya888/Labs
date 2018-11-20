#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include "bmp.h"

read_error_code_t from_bmp(FILE* in, image_t* const read) {
	bmp_header_t bmp_header;
	uint32_t i;
	if (fread(&bmp_header, sizeof(bmp_header_t), 1, in) != 1) {
		if (feof(in)) return READ_INVALID_BITS; // Функция feof проверяет, достигнут ли конец файла, связанного с потоком, через параметр; 
		return READ_INVALID_SIGNATURE;         // Возвращается значение, отличное от нуля, если конец файла был действительно достигнут.
	}
	if (bmp_header.bfType != 0x4D42 || bmp_header.biBitCount != 24) return READ_INVALID_HEADER;
	read->height = bmp_header.biHeight;
	read->width = bmp_header.biWidth;
	read->data = (pixel_t*)malloc(read->height * read->width * sizeof(pixel_t));
	fseek(in, bmp_header.bOffBits, SEEK_SET);
	for (i = 0; i < read->height; ++i) {
		if (fread(read->data + (read->height - i - 1) * read->width, sizeof(pixel_t), read->width, in) != read->width) return READ_INVALID_BITS;
		fseek(in, read->width % 4, SEEK_CUR);
	}
	return READ_OK;
}

image_t rotate(image_t const source) {
	image_t rotated_pic;
	uint32_t i, j;
	rotated_pic.height = source.width;
	rotated_pic.width = source.height;
	rotated_pic.data = (pixel_t*)malloc(rotated_pic.width * rotated_pic.height * sizeof(pixel_t));
	for (i = 0; i < source.height; ++i) {
		for (j = 0; j < source.width; ++j) {
			*(rotated_pic.data + j * rotated_pic.width + rotated_pic.width - i - 1) = *(source.data + i * source.width + j);
		}
	}
	return rotated_pic;
}

write_error_code_t to_bmp(FILE* out, image_t const* img) {
	uint32_t i;
	char* str = "000";
	for (i = 0; i < img->height; ++i) {
		if (fwrite(img->data + (img->height - i - 1) * img->width, sizeof(pixel_t), img->width, out) != img->width) return WRITE_ERROR;
	//	fflush(out);   ???
		fwrite(str, img->width % 4, 1, out);
	//	fflush(out);
	}
	return WRITE_OK;
}

void copy_header(FILE* in, FILE* out, int flag) {
	bmp_header_t bmp_header;
	uint32_t var;
	fseek(in, 0, SEEK_SET);
	fread(&bmp_header, sizeof(bmp_header_t), 1, in);
	fflush(in);
	var = bmp_header.biHeight;
	if (flag) {		
		bmp_header.biHeight = bmp_header.biWidth;
		bmp_header.biWidth = var;
	}

	bmp_header.bfileSize = bmp_header.bOffBits + 3 * bmp_header.biHeight * bmp_header.biWidth + bmp_header.biHeight * (bmp_header.biWidth % 4);
	fwrite(&bmp_header, sizeof(bmp_header_t), 1, out);
	//fflush(out);
	fseek(out, bmp_header.bOffBits, SEEK_SET);
	//fflush(&bmp_header);
}

