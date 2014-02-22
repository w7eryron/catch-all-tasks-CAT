catch-all-tasks-CAT
===================

Project for recording all of my various booking tasks 

++++++++++++++++++


TpepTwo ... 

Ron Wright, Teacher, Camas WA

February 2014

an Android List/details App used to record evidence for evaluations

Very closely follows the "Criminal Intent" demonstration App in "Big Nerd Ranch Android Programming" book. 

currently only for my cell phone - samsung running froyo, ... might work on something else. ??

++++++++++++++++++++++++++++++++++++++++++++ 
FUTURE:

If I had the "code" variable up and running, then this one app could be used for recording activities to atribute to very different tasks:
- TPEP evaluation criteria as orignally intended (code letter "t", and/or numbers)
- Hours for CTE requirement (code letter "c")
- hours for tripay (code letter "p")
- hours for coaching (code letter "s")
- hours for Staff Development (code letter "d")
then I would want a sort and send feature for each. 

++++++++++++++++++++++++++++++++++++++++++++

Variables in "LogItem" object:

mId - randomly generatted

mTitle - 4-word description of evidence piece (EP)

mDate - date EP occurred

mDescription - max two-sentence description of the EP

mCriteria - Washington State Criteria number and possibly description - not yet implemented

mHours - number of hours in the EP, to nearest tenth

mDocTitle - title for a supporting document ... may someday become a list

mDocLink - link for supporting document - TBD ... ditto

mPhoto - - no clue how to do this yet, may be able to follow BigNerd example

mTask -  a variaable to use to sort the various tasks that I will record - TBD

++++++++++++++++++++++++++++++++++++++++++++ 

first view shows a list of the items sorted by date or by criteria, vertical scroll

second view shows a detail of a given item, horizontal scroll


five buttons: 

  new item - following BigNerd
  
  statistics on entire list - TBD
  
  toggle list view between "sort by date" and "sort by criteria" - TBD
  
  send entire file to someone via email, probably several ways - done 2/17
  
  take a photo - TBD, probably following BigNerd
  

Database or similar relating Washington State Criteria Number to short descriiption of each. About 30 items. TBD

  
++++++++++++++++++++++++++++++++++++++++++++ 

Statistics button - all TBD as of 2/15

  total hours
  
  total items
  
  total items by criteria number
  
  total items in "student learning" sub-categorys
  
  graph showing total items by criteria number

++++++++++++++++++++++++++++++++++++++++++++ 




