#include <stdio.h>

#include <stdlib.h>

#include <stdint.h>

#include "bmp.h"

void inc_pix(float[3][4], float[3][4]);



read_error_code_t from_bmp(FILE* in, image_t* const read) {

	bmp_header_t bmp_header;

	uint32_t i;

	if (fread(&bmp_header, sizeof(bmp_header_t), 1, in) != 1) {

		if (feof(in)) return READ_INVALID_BITS; 

		return READ_INVALID_SIGNATURE;   

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

	rotated_pic.height = source.height;

	rotated_pic.width = source.width;

	rotated_pic.data = (pixel_t*)malloc(rotated_pic.width * rotated_pic.height * sizeof(pixel_t));

	for (i = 0; i < source.height; ++i) {

		for (j = 0; j < source.width; ++j) {

			/*(rotated_pic.data + j * rotated_pic.height + rotated_pic.height - i - 1) = *(source.data + i * source.width + j);*/

			*(rotated_pic.data + i * rotated_pic.width + j) = *(source.data + i * source.width + j);

		}

	}

	return rotated_pic;

	

}



image_t new_color(image_t const source){

	/*	float constans[3][4] = {{ .393f, .769f, .189f, .393f},

					        { .349f, .686f, .168f, .349f},

					        { .272f, .543f, .131f, .272f}};

*/

	

	uint32_t i, j;

	pixel_t pix1;

	pixel_t pix2;  

	/*  

	float lol[3][4] = {{(float)pix1.b, (float)pix1.b, (float)pix1.b, (float)pix2.b},

						{(float)pix1.g, (float)pix1.g, (float)pix1.g, (float)pix2.g},

						{(float)pix1.r, (float)pix1.r, (float)pix1.r, (float)pix2.r}};

						

	float constans[3][4] = {{ .393f, .349f, .272f, .393f},

					         { .769f, .686f, .543f, .349f},

					         { .189f, .168f, .131f, .272f}};	

	*/

	for (i = 0; i < source.height*source.width*3/4; i++) {

		pix1 = *(source.data + i*4/3);

		pix2 = *(source.data + i*4/3+1);

/*		

		if(i==33){

				printf("%d\n",pix1.b);

				printf("%d\n",pix1.g);

				printf("%d\n",pix1.r);

		}

*/		

		if(i%3 == 0){

			float lol[3][4] = {{(float)pix1.b, (float)pix1.b, (float)pix1.b, (float)pix2.b},

						{(float)pix1.g, (float)pix1.g, (float)pix1.g, (float)pix2.g},

						{(float)pix1.r, (float)pix1.r, (float)pix1.r, (float)pix2.r}};

						

			float constans[3][4] = {{ .393f, .349f, .272f, .393f},

					         { .769f, .686f, .543f, .349f},

					         { .189f, .168f, .131f, .272f}};	

			inc_pix(lol, constans);	

			

			pix1.b = 255;

			pix1.g = 255;

			pix1.r = 255;

			pix2.b = 255;

			if(lol[0][0]<=255){

				pix1.b = (char)lol[0][0];

			}

			if(lol[0][1]<=255){

				pix1.g = (char)lol[0][1];

			}

			if(lol[0][2]<=255){

				pix1.r = (char)lol[0][2];

			}

			if(lol[0][3]<=255){

				pix2.b = (char)lol[0][3];

			}		

			

			(source.data + i*4/3)->b = pix1.b;

			(source.data + i*4/3)->g = pix1.g;

			(source.data + i*4/3)->r = pix1.r;

			(source.data + i*4/3+1)->b = pix2.b;

					         						

		}else if(i%3 == 1){

			float lol[3][4] = {{(float)pix1.b, (float)pix1.b, (float)pix2.b, (float)pix2.b},

						{(float)pix1.g, (float)pix1.g, (float)pix2.g, (float)pix2.g},

						{(float)pix1.r, (float)pix1.r, (float)pix2.r, (float)pix2.r}};	

							

			float constans[3][4] = {{.349f, .272f,.393f, .349f},

					         {.686f, .543f,.769f, .686f},

					         {.168f, .131f,.189f, .168f}};	

			inc_pix(lol, constans);

			

			pix1.g = 255;

			pix1.r = 255;

			pix2.b = 255;

			pix2.g = 255;

			if(lol[0][0]<=255){

				pix1.g = (char)lol[0][0];

			}

			if(lol[0][1]<=255){

				pix1.r = (char)lol[0][1];

			}

			if(lol[0][2]<=255){

				pix2.b = (char)lol[0][2];

			}

			if(lol[0][3]<=255){

				pix2.g = (char)lol[0][3];

			}		

			

			(source.data + i*4/3)->g = pix1.g;

			(source.data + i*4/3)->r = pix1.r;

			(source.data + i*4/3+1)->b = pix2.b;

			(source.data + i*4/3+1)->g = pix2.g;	

		}else if(i%3 == 2){

			float lol[3][4] = {{(float)pix1.b, (float)pix2.b, (float)pix2.b, (float)pix2.b},

						{(float)pix1.g, (float)pix2.g, (float)pix2.g, (float)pix2.g},

						{(float)pix1.r, (float)pix2.r, (float)pix2.r, (float)pix2.r}};	

							

			float constans[3][4] = {{ .272f,.393f, .349f, .272f},

					         { .543f,.769f, .686f, .543f},

					         { .131f,.189f, .168f, .131f}};		

			inc_pix(lol, constans);

			

			pix1.r = 255;

			pix2.b = 255;

			pix2.g = 255;

			pix2.r = 255;

			if(lol[0][0]<=255){

				pix1.r = (char)lol[0][0];

			}

			if(lol[0][1]<=255){

				pix2.b = (char)lol[0][1];

			}

			if(lol[0][2]<=255){

				pix2.g = (char)lol[0][2];

			}

			if(lol[0][3]<=255){

				pix2.r = (char)lol[0][3];

			}	

			

			(source.data + i*4/3)->r = pix1.r;

			(source.data + i*4/3+1)->b = pix2.b;

			(source.data + i*4/3+1)->g = pix2.g;

			(source.data + i*4/3+1)->r = pix2.r;			

		}



/*		

		if(i==33){

				printf("%d\n",pix1.b);

				printf("%d\n",pix1.g);

				printf("%d\n",pix1.r);

		}

*/

		

	}

	return source;				        				        	

}



write_error_code_t to_bmp(FILE* out, image_t const* img) {

	uint32_t i;

	char* str = "000";

	for (i = 0; i < img->height; ++i) {

		if (fwrite(img->data + (img->height - i - 1) * img->width, sizeof(pixel_t), img->width, out) != img->width) return WRITE_ERROR;



		fwrite(str, img->width % 4, 1, out);

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

	fseek(out, bmp_header.bOffBits, SEEK_SET);

}

