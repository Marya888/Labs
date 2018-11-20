#include "bmp.h"



int main( int args, char** argv) 

{

	FILE *picture_in;

	FILE *picture_out;

	image_t image_read;

	image_t image_write;

	read_error_code_t e_code;

	int rez;

	char namefile[256];

	char *res="_result.bmp";



    printf("Enter name of file\n");

	scanf("%s", namefile);

		

	picture_in = fopen(namefile, "rb"); //rb-read двоичный файл

	if (picture_in == NULL) {

		perror("Lol! No such file");

		return 1;

	}



	e_code = from_bmp(picture_in, &image_read);



	

	if (e_code == READ_OK) {

		image_write = rotate(image_read);

		

	//	printf("%s\n",strtok("lol","o"));

		// strcat(strtok(namefile,".bmp"), res)

		picture_out = fopen("result.bmp", "wb");

		copy_header(picture_in, picture_out, 1);

		to_bmp(picture_out, &image_write);

		fclose(picture_out);

//		fflush(picture_out);

		fclose(picture_in);

		printf("Good job!\n");

		

	} else

	if (e_code == READ_INVALID_SIGNATURE) printf("File can't be read! READ_INVALID_SIGNATURE\n");

		else if (e_code == READ_INVALID_BITS) printf("File can't be read! READ_INVALID_BITS\n");

			else if (e_code == READ_INVALID_HEADER) printf("File can't be read! READ_INVALID_HEADER\n");

	fclose(picture_in);

	free(image_write.data);

	return 0;

}

