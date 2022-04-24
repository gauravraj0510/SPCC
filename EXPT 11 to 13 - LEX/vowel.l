%{
 
 /*To find whether given letter is a vowel or not*/
 #undef yywrap
 #define yywrap() 1
 void display(int);
%}
 
%%
 
[a|e|i|o|u|] {
 int flag=1;
 display(flag);
 return;
 }
 
.+ {
 int flag=0;
 display(flag);
 return;
 }
 
%%
 
void display(int flag)
{
 if(flag==1)
 printf("The given letter [%s] is a vowel",yytext);
 else
 printf("The given letter [%s] is NOT a vowel",yytext); 
}
 
main()
{
 printf("Enter a letter to check if it is a vowel or not: ");
 yylex(); 
}


// OUTPUT
// Enter a letter to check if it is a vowel or not: a
// The given letter [a] is a vowel