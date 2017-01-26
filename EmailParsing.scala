//Ques 2- Write regular expression for email parsing.


object EmailParsing
{
	def main(args:Array[String])=
	{
		val EMAIL = """([a-zA-Z0-9_.]+)@([a-zA-Z0-9.]+)""".r
  		val EMAIL(user_1, domain_1) = "nitin.aggarwal@knoldus.in"
		val EMAIL(user_2, domain_2) = "vandana.yadav@knoldus.in"
      		println("User = "+user_1+"\n"+"Domain = "+domain_1)
		println("User = "+user_2+"\n"+"Domain = "+domain_2)

	}
}