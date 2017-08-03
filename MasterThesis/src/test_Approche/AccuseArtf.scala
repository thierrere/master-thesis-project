package test_Approche

import java.util.Calendar;
import java.util.Date;

class AccuseArtf extends Artefact{
    
  private var text:String="";
  private var da=Calendar.getInstance().getTime();
  private var date:String=(da.getDate())+"/"+(da.getMonth()+1)+"/"+(da.getYear()+1900)+" "+(da.getHours())+"h"+(da.getMinutes())+"mn"+(da.getSeconds())+"s";
  println(this.date);
  
  def getText():String={
		return text;
	}

  def setText(text:String){
		this.text = text;
	}

  def getDate():String={
		return date;
	}

  def setDate(date:String){
		this.date = date;
	}
  
}