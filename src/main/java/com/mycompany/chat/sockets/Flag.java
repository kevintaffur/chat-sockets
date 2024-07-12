package com.mycompany.chat.sockets;

public class Flag
{
   public Flag(boolean aFlag)
   {
      flag = aFlag;
   }
   
   public void setFlag(boolean newValue)
   {
      flag = newValue;
   }
   
   public boolean getFlag()
   {
      return flag;
   }
   
   boolean flag;
}
