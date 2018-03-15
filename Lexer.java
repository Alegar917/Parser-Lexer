import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
public class Lexer {

        final int MAXSIZE=3000;
        char buffer[];  // this will hold the chars of the file
        int bufSize=0;  // how many chars their actually are
        int index=0;    // the location within buffer as we scan
        int line=1;
        public Lexer(String fileName) {
            this.buffer = new char[MAXSIZE];
            getInput(fileName);

        }
        private void getInput(String fileName) {
            File inputFile = new File (fileName);
            try (FileReader reader = new FileReader(inputFile)) {
                this.bufSize = reader.read(this.buffer,0,1000);
            } catch(FileNotFoundException fnf) {
                System.out.println("File not found.");
                System.out.println(fnf.getMessage());
                System.exit(1);
            } catch(IOException exc) {
                System.out.println("IO Exception");
                System.out.println(exc.getMessage());
                System.exit(1);
            }

        }
        public Token getNextToken(){
            String t="";
            String type="";
            char c = this.buffer[this.index];

            while (Character.isWhitespace(c)){
                if(c=='\n'){
                    this.line++;
                }
                this.index++;
                c=this.buffer[this.index];
            }
            if ((c>='a')&&(c<='z')||(c>='A')&&(c<='Z')){
                t=this.getIdentifer();
                type="Identifer"; 
                Token tok=new Token(type,t);
                return tok;
            }
            if (c=='='){
                t=t+c;
                type="AssignOp";
                this.index++;
                Token tok=new Token(type,t);
                return tok;
            }
            if ((c>='0')&&(c<='9')){
                t=this.getInteger();
                type="Integer";
                Token tok=new Token(type,t);
                return tok;
            }
            if (c=='+'){
                t=t+c;
                type="PlusOp";
                this.index++;
                Token tok=new Token(type,t);
                return tok;
           }
           if (((c>='!')&&(c<='*')||(c>=',')&&(c<='/')||(c>=':')&&(c<='<')||(c>='>')&&(c<='@')||(c>='[')&&(c<='`')||(c>='{')&&(c<='~'))){
                t=t+c;
                type="UnknownOp";
                this.index++;
                Token tok=new Token(type,t);
                return tok;
            }
            if(this.index==this.bufSize){
                 t="-";
                 type="EndOfFile";
                 this.index++;
            }
            Token tok=new Token(type,t);
            return tok;
    }


        private String getIdentifer(){
            String s="";
            char c = this.buffer[this.index];
            while(((c>='a')&&(c<='z')||(c>='0')&&(c<='9')||(c>='A')&&(c<='Z'))){
                s=s+c;
                this.index++;
                c = this.buffer[this.index];
            }
            return s;
        }
        private String getInteger(){
            String n="";
            char c = this.buffer[this.index];
            while((c>='0')&&(c<='9')){
                n=n+c;
                this.index++;
                c = this.buffer[this.index];
            }
            return n;
        }
        // just print out buffer, mainly for debugging
        public String toString() {
            int i=0;
            String s="";
            while (i<this.bufSize) {
                s = s+this.buffer[i];
                i++;
            }
            return s;
        }
        // this method scans the buffer starting at index. If it finds a lowercase letter, it
        // continues to scan and adds the lower case letters to a string until a non lower-case letter
        // is scanned. this.index is incremented and will end up on the last lowercase letter
        // NOTE: THIS IS A SAMPLE METHOD YOU DO NOT NEED IN LEXER
        // public String firstWord() {
        //     String s="";
        //     char c = this.buffer[this.index];
        //     while ((c>='a') && (c<='z')) {
        //         s=s+c;
        //         this.index++;
        //         c = this.buffer[this.index];
        //     }
        //     return s;

        // }

        public static void main(String[] args) {
            int i=0;
            Lexer lexer = new Lexer("test1.txt");
            // System.out.println(lexer.bufSize);
            // System.out.println(lexer.firstWord());

            while(lexer.index<=lexer.bufSize){
                System.out.println(lexer.getNextToken());
            }
        }

}
