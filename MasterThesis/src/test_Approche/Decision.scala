package test_Approche

import java.util.Calendar;
import java.util.Date;

class Decision extends Artefact {
  
	private var editeur:String="";
	private var eassoc:String="";
	private var decision:String="";
	private var relecteur:String="";
	private var observaSuggest:String="";
	private var lecture:String="";
	//date 
	private var da=Calendar.getInstance().getTime();
  private var date:String=(da.getDate())+"/"+(da.getMonth()+1)+"/"+(da.getYear()+1900)+" "+(da.getHours())+"h"+(da.getMinutes())+"mn"+(da.getSeconds())+"s";
  
  def setEditeur(editeur:String){
		this.editeur = editeur;
	}

  def getEditeur():String={
		return editeur;
	}

  def setEassoc(eassoc:String){
		this.eassoc = eassoc;
	}

  def getEassoc():String={
		return eassoc;
	}

 def setDecision(decision:String){
		this.decision = decision;
	}

  def getDecision():String={
		return decision;
	}

  def setRelecteur(relecteur:String){
		this.relecteur = relecteur;
	}

  def getRelecteur():String={
		return relecteur;
	}

  def setObservaSuggest(observaSuggest:String) {
		this.observaSuggest = observaSuggest;
	}

  def getObservaSuggest():String={
		return observaSuggest;
	}

  def setLecture(lecture:String){
		this.lecture = lecture;
	}

  def getLecture():String={
		return lecture;
	}
  def getDate():String={
		  return date;
  }


}