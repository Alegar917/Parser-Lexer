public class Parser{
	public Lexer lexer;
	private Token oldToken;
	private String message;
	private String end;
	private Token aToken;
	private int line=2;

	public Parser(){
		Lexer lexer=new Lexer("test1.txt");
		this.lexer=lexer;
	}
	public void parseProgram(){
		String output="";
		while(this.end!="EndOfFile"){
			output=parseAssignment();
			if(this.message!="Valid Program"){
				break;
			}		
		}
		System.out.println(output);
	}
	public String parseAssignment(){
		this.message="Valid Program";
		if(parseId());
		else{
			this.message="Expecting Identifer on line"+" "+lexer.line;
			return message;
		}
		
		if(parseAssignOp());
		else{
			this.message="Expecting AssignOp on line"+" "+lexer.line;
			return message;
		}
		if(parseExpression());
		return this.message; 
		
	}
	private Boolean parseId(){
		if(this.oldToken!=null){
			if(oldToken.getType()=="Identifer"){
				this.oldToken=null;
				return true;
			}
		}
	 	Token t=lexer.getNextToken();
		return(t.getType()=="Identifer");
	}
	private Boolean  parseAssignOp(){
		if(this.aToken!=null){
			if(aToken.getType()=="AssignOp"){
				this.aToken=null;
				return true;
			}
		}
		Token t=lexer.getNextToken();
		return(t.getType()=="AssignOp");
	}
	private Boolean parseExpression(){
		Token t=lexer.getNextToken();
		if (t.getType()=="Identifer"||t.getType()=="Integer"){
			t=lexer.getNextToken();
			if(t.getType()=="EndOfFile"){
				this.end="EndOfFile";
				return true;
			}

			if (t.getType()=="Identifer"||t.getType()=="Integer"){
				while(t.getType()=="Identifer"||t.getType()=="Integer"){
				this.oldToken=t;
				t=lexer.getNextToken();
				this.aToken=t;	
				return true;
				}
			}
			if(t.getType()=="AssignOp"){
				this.message="Expecting Identifer or integer on line"+" "+lexer.line;
				return false;
			}
			if(t.getType()=="UnknownOp"){
				this.message="Expecting Identifer or integer on line"+" "+lexer.line;
					return false;
			}
			if(t.getType()=="PlusOp"){
				t=lexer.getNextToken();
				if (t.getType()=="Identifer"&&lexer.line==this.line||t.getType()=="Integer"&&lexer.line==this.line){
					this.message="Expecting Identifer or AssignOp on line"+" "+lexer.line;
					return false;
				}
				if (t.getType()=="UnknownOp"){
					this.message="Expecting Identifer or integer on line"+" "+lexer.line;
					return false;
				}
				if(t.getType()=="AssignOp"){
					this.message="Expecting Identifer or integer on line"+" "+lexer.line;
					return false;
				}
				if(t.getType()=="PlusOp"){
					this.message="Expecting Identifer or integer on line"+" "+lexer.line;
					return false;
				}
				if(t.getType()=="EndOfFile"){
					this.message="Expecting Identifer or Integer on line"+" "+lexer.line;
					return false;
				}
				if (t.getType()=="Identifer"||t.getType()=="Integer"){
					while(t.getType()=="Identifer"||t.getType()=="Integer"){
					this.oldToken=t;
					t=lexer.getNextToken();
					this.aToken=t;
					if(t.getType()=="PlusOp"){
						t=lexer.getNextToken();
						if(t.getType()=="EndOfFile"){
							this.message="Expecting Identifer or Integer on line"+" "+lexer.line;
							return false;
						}
						if(t.getType()=="UnknownOp"){
							this.message="Expecting Identifer or AssignOp on line"+" "+lexer.line;
							return false;
						}
					}
				}
			}
		}
			if(t.getType()=="EndOfFile"){
				this.end="EndOfFile";
				}
		this.line++;
		return true;
		}
		else{
			this.message="Expecting Identifer or integer on line"+" "+lexer.line;
			return false;
		}
	}

 	public static void main(String[] args) {
 		Parser parser=new Parser();
 		parser.parseProgram();
 }
}
