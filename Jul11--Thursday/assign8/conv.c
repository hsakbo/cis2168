#include <stdio.h>
#include <string.h>


int main(){

  
  FILE *f = fopen("words.txt", "r");
  
  FILE *gen = fopen("gen.txt", "w");
  
  int save; 
  
  char *str;

  
  
  while(!feof(f)){

    str = (char*) malloc(sizeof(char) * 100);
    save = 0;
    fscanf(f, "%s", str);
    //printf("a\n");
    for(int i = 0; i < strlen(str); i++){      
      save += (int) str[i];
      
    }
    fprintf(gen, "%d\n", save);
    free(str);

  }

  fclose(f);
  fclose(gen);

}
