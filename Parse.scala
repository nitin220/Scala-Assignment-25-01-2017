//Ques-1.	Complete  Extractor definition  for URL parsing

object URL
{
	def apply(protocol:String,domain:String,path:String,params:Map[String,String]):String=
	{
		val listParams=params map(element=>element._1+"="+element._2+"&")
		def inner(result:String,list:List[String]):String=
		{
			list match
			{
				case Nil=> result
				case x :: tail=>inner(result+x,tail)
			}
		}
		val stringParam=inner("",listParams.toList)
		val finalParams=stringParam slice(0,stringParam.length-1)
		val result:String=protocol+"://"+domain+path+"?"+finalParams
		result
	}
	def unapply(url:String):Option[(String,String,String,Map[String,String])]=
	{
		val protocolAndRest = url.split("://")
		if(protocolAndRest.length==2)
		{
			val protocol = protocolAndRest(0)
			val domainPathParams = protocolAndRest(1).split("\\?")
			if(domainPathParams.length==2)
			{
				val params = domainPathParams(1)
				val domainPath = domainPathParams(0)split(".com")
				if(domainPath.length==2)
				{
					val domain = domainPath(0)+".com"
					val path = domainPath(1)
					val toBeMapParams = params.split("&")
					val toBeMapParamsList=toBeMapParams.toList.map(element=>element.split("="))
					val toBeMapParamsListOfString=toBeMapParamsList.map(element=>(element(0),element(1)))
					val paramsMap=toBeMapParamsListOfString.toMap
					Some(protocol,domain,path,paramsMap)
				}
				else None
			}
			else None
		}
		else None
		
	}
	
}

object Parse extends App
{
	val url="https://aws.amazon.com/console/home?state=hash&isauthcode=true&code=112"
	url match 
	{
		case URL(protocol,domain,path,parama)=>
			println("Protocol = "+protocol+"\nDomain = "+domain+"\nPath = "+path+"\nParams = "+parama)
		
		case _=> println("INVALID URL")
	}

}
