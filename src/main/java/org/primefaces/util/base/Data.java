package org.primefaces.util.base;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**Classe parecida com DataUtil
 * 
 * Verificar necessidade / viabilidade de exclusao de uma, junção, ou extensão a outra
 * 
 * 
 * */
public class Data {

    public static String FORMAT_DATA = "yyyy-MM-dd"; //Formato de data do MySQL
    public static String FORMAT_DATA_BR = "dd/MM/yyyy";
    public static String FORMATO_FUNCAO_DATA = "NOW();"; //GETDATE()
    public static String FORMAT_DATATIME_BR = "dd/MM/yyyy HH:mm:ss";
    public static String FORMAT_DATATIME_USA = "yyyy-MM-dd HH:mm:ss";

    public static String getDataAtual() {
        try {
            DateFormat df = new SimpleDateFormat(FORMAT_DATA);
            Date date = new Date();
            return df.format(date);
        } catch (Exception e) {
            //System.out.println("Ocorreu no mï¿½todo getDate(java.sql.date) ao tentar formatar a data informada. Erro: " + e);
        }
        return null;
    }

    public static String getDataAtual(String pattern) {
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            Date date = new Date();
            return df.format(date);
        } catch (Exception e) {
            //System.out.println("Ocorreu no mï¿½todo getDate(java.sql.date) ao tentar formatar a data informada. Erro: " + e);
        }
        return null;
    }

    public static String getDateTime() {
        try {
            DateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
            Date date = new Date();
            return df.format(date);
        } catch (Exception e) {
            // System.out.println("Ocorreu no mï¿½todo getDate(java.sql.date) ao tentar formatar a data informada. Erro: " + e);
        }
        return null;
    }

    public static String getDateTime(Timestamp date) {

        String FvaRetorno = "";
        if (date == null) {
            return "";
        }
        try {
            java.util.Date newDate = new java.util.Date();
            newDate.setTime(date.getTime());

            DateFormat df = new SimpleDateFormat(FORMAT_DATATIME_BR);
            df.setLenient(false);
            FvaRetorno = df.format(newDate);
        } catch (Exception e) {
            //System.out.println("Ocorreu no mï¿½todo getDate(java.sql.date) ao tentar formatar a data informada. Erro: " + e);
//            e.printStackTrace();
        }
        return FvaRetorno;
    }

    public static String getDateTime(Date date) {

        String FvaRetorno = "";
        if (date == null) {
            return "";
        }
        try {
            java.util.Date newDate = new java.util.Date();
            newDate.setTime(date.getTime());

            DateFormat df = new SimpleDateFormat(FORMAT_DATATIME_BR);
            df.setLenient(false);
            FvaRetorno = df.format(newDate);
        } catch (Exception e) {
            //System.out.println("Ocorreu no mï¿½todo getDate(java.sql.date) ao tentar formatar a data informada. Erro: " + e);
//            e.printStackTrace();
        }
        return FvaRetorno;
    }

    public static String getDateTimeSQL(java.sql.Date date) {
        String FvaRetorno = "";
        if (date == null) {
            return "";
        }
        try {
            java.util.Date newDate = new java.util.Date();
            newDate.setTime(date.getTime());

            DateFormat df = new SimpleDateFormat(FORMAT_DATATIME_BR);
            df.setLenient(false);
            FvaRetorno = df.format(newDate);

        } catch (Exception e) {
            //System.out.println("Ocorreu no mï¿½todo getDate(java.sql.date) ao tentar formatar a data informada. Erro: " + e);
//            e.printStackTrace();
        }
        return FvaRetorno;
    }

    public static java.sql.Date getDateTimeSQL(String dataStr) throws ParseException {
        java.sql.Date FvaDataRetorno = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATATIME_BR);
            df.setLenient(false);
            FvaDataRetorno = new java.sql.Date(df.parse(dataStr).getTime());
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro na função getDateParse(String dataStr, String pattern). Erro: " + e);
        }
        return FvaDataRetorno;
    }

    public static java.sql.Date getDateTimeSQLUSA(String dataStr) throws ParseException {
        java.sql.Date FvaDataRetorno = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATATIME_USA);
            df.setLenient(false);
            FvaDataRetorno = new java.sql.Date(df.parse(dataStr).getTime());
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro na fun��o getDateParse(String dataStr, String pattern). Erro: " + e);
        }
        return FvaDataRetorno;
    }

    public static java.util.Date getDateTimeUtilSQLUSA(String dataStr) throws ParseException {
        java.sql.Date FvaDataRetorno = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATATIME_USA);
            df.setLenient(false);
            FvaDataRetorno = new java.sql.Date(df.parse(dataStr).getTime());
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro na fun��o getDateParse(String dataStr, String pattern). Erro: " + e);
        }
        return FvaDataRetorno;
    }

//Fun��es Especiais
//-------------------------------------------------------------------------------------------------------------------------------------------------
    public static java.util.Date getDateParse(String dataStr, String pattern) throws ParseException {
        java.util.Date FvaDataRetorno = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);
            FvaDataRetorno = df.parse(dataStr);
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro na função getDateParse(String dataStr, String pattern). Erro: " + e);
        }
        return FvaDataRetorno;
    }

    public static String getDateParse(java.util.Date data, String pattern) {
        String FvaDataRetorno = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);
            FvaDataRetorno = df.format(data);
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro na função getDateParse(String dataStr, String pattern). Erro: " + e);
        }
        return FvaDataRetorno;
    }

    private static java.util.Date getDateParse(String dataStr) throws ParseException {
        java.util.Date FvaDataRetorno = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
            df.setLenient(false);
            FvaDataRetorno = df.parse(dataStr);
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro na fun��o getDateParse(String dataStr). Erro: " + e);
        }
        return FvaDataRetorno;
    }

    private static String getDateParse(java.util.Date data) {
        String FvaDataRetorno = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
            df.setLenient(false);
            FvaDataRetorno = df.format(data);
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro na fun��o getDateParse(String dataStr, String pattern). Erro: " + e);
        }
        return FvaDataRetorno;
    }

//Fim das Fun��es Especiais
//-------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------
//Fun��o para adicionar dias em data
    public static String dateAdd(String dateStr, int dias) {
        String FvaRetorno = "";
        try {

            SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
            df.setLenient(false);
            java.util.Date dataTemp = df.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataTemp);
            calendar.add(Calendar.DATE, dias);
            FvaRetorno = df.format(calendar.getTime());
        } catch (java.text.ParseException e) {
            //System.out.println("Ocorreu um erro no m�todo dateAdd(String dateStr, int dias, String pattern). Erro: " + e);
        }
        return FvaRetorno;
    }
//Fim da fun��o para adicionar dias em data
//---------------------------------------------------------------------------------------------------------------------------------------------
    public static Date dateAdd(Date dateStr, int dias) {
        Date FvaRetorno = null;
        try {
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateStr);
            calendar.add(Calendar.DATE, dias);
            FvaRetorno = (calendar.getTime());
            
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro no mï¿½todo dateAdd(String dateStr, int dias, String pattern). Erro: " + e);
        }
        return FvaRetorno; 
    }
//---------------------------------------------------------------------------------------------------------------------------------------------
//Fun��o para checagem de data
    @SuppressWarnings("removal")
	public static boolean isDate(String dateStr) throws ParseException {
        dateStr = dateStr + "";
        boolean FvaRetorno = true;
        if (dateStr.equalsIgnoreCase("null") || dateStr.length() == 0) {
            return false;
        }
        try {
            DateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
            Calendar cal = new GregorianCalendar();
            cal.setTime(df.parse(dateStr));

            String[] data = dateStr.split("/");
            String dia = data[0];
            String mes = data[1];
            String ano = data[2];
            if ((new Integer(dia)).intValue() != (new Integer(cal.get(Calendar.DAY_OF_MONTH))).intValue()) {
                FvaRetorno = false;
            } else if ((new Integer(mes)).intValue() != (new Integer(cal.get(Calendar.MONTH) + 1)).intValue()) {
                FvaRetorno = false;
            } else if ((new Integer(ano)).intValue() != (new Integer(cal.get(Calendar.YEAR))).intValue()) {
                FvaRetorno = false;
            }
        } catch (Exception e) {
            FvaRetorno = false;
            //System.out.println("Ocorreu um erro no m�todo isDate(String dateStr). Erro: " + e);
        }
        return FvaRetorno;
    }
//Função para checagem de data
    public static boolean isDate(Date dateUtil) {
        
        boolean FvaRetorno = true;
        if (dateUtil == null) {
            return false;
        }
        return FvaRetorno;
    }
    public static boolean isDateTime(String dateStr) throws ParseException {
        dateStr = dateStr + "";
        boolean FvaRetorno = true;
        if (dateStr.equalsIgnoreCase("null") || dateStr.length() == 0) {
            return false;
        }
        try {
            DateFormat df = new SimpleDateFormat(FORMAT_DATATIME_BR);
            Calendar cal = new GregorianCalendar();
            cal.setTime(df.parse(dateStr));

            /*String[] data = dateStr.split("/");   
             String dia = data[0];   
             String mes = data[1];   
             String ano = data[2];   
             if ( (new Integer(dia)).intValue() != (new Integer(cal.get(Calendar.DAY_OF_MONTH))).intValue()){   
             FvaRetorno=false;
             }else if ( (new Integer(mes)).intValue() != (new Integer(cal.get(Calendar.MONTH)+1)).intValue()){   
             FvaRetorno=false;
             }else if ( (new Integer(ano)).intValue() != (new Integer(cal.get(Calendar.YEAR))).intValue()){   
             FvaRetorno=false;
             }*/
        } catch (Exception e) {
            FvaRetorno = false;
            System.out.println("Ocorreu um erro no método isDateTime(String dateStr) = " + dateStr + ". Erro: " + e);
            e.printStackTrace();
        }        
        return FvaRetorno;
    }

    @SuppressWarnings("removal")
	public static boolean isDate(String dateStr, String patern) throws ParseException {
        dateStr = dateStr + "";
        boolean FvaRetorno = true;
        if (dateStr.equalsIgnoreCase("null") || dateStr.length() == 0) {
            return false;
        }
        try {
            DateFormat df = new SimpleDateFormat(patern);
            Calendar cal = new GregorianCalendar();
            cal.setTime(df.parse(dateStr));

            DateFormat df2 = new SimpleDateFormat(FORMAT_DATA_BR);
            df2.setLenient(false);
            dateStr = df2.format(cal.getTime());

            String[] data = dateStr.split("/");
            String dia = data[0];
            String mes = data[1];
            String ano = data[2];
            if ((new Integer(dia)).intValue() != (new Integer(cal.get(Calendar.DAY_OF_MONTH))).intValue()) {
                FvaRetorno = false;
            } else if ((new Integer(mes)).intValue() != (new Integer(cal.get(Calendar.MONTH) + 1)).intValue()) {
                FvaRetorno = false;
            } else if ((new Integer(ano)).intValue() != (new Integer(cal.get(Calendar.YEAR))).intValue()) {
                FvaRetorno = false;
            }
        } catch (Exception e) {
            FvaRetorno = false;
            //System.out.println("Ocorreu um erro no m�todo isDate(data) ao tentar verificar se a data infornada � uma data v�lida. Erro: " + e);
        }
        return FvaRetorno;
    }
//Fim da fun��o para checagem de data
//---------------------------------------------------------------------------------------------------------------------------------------------

//---------------------------------------------------------------------------------------------------------------------------------------------
//Fun��o para retornar/formatar data
    public static String getDate() {
        String FvaDataRetorno = "";
        try {
            java.util.Date data = new java.util.Date();
            FvaDataRetorno = getDateParse(data);
        } catch (Exception e) {

        }
        return FvaDataRetorno;
    }

    public static java.util.Date getDateNow() {
        try {
            return new java.util.Date();
        } catch (Exception e) {

        }
        return null;
    }

    public static String getDate(String pattern) {
        String FvaDataRetorno = "";
        try {
            java.util.Date data = new java.util.Date();
            FvaDataRetorno = getDateParse(data, pattern);
        } catch (Exception e) {

        }
        return FvaDataRetorno;
    }
//Fim da fun��o para retornar/formatar data
//---------------------------------------------------------------------------------------------------------------------------------------------

//---------------------------------------------------------------------------------------------------------------------------------------------
//Fun��o para converter para outro formato
    public static String getDateConvertFormat(String data) throws ParseException {
        String FvaDataRetorno = "";
        try {
            if (isDate(data)) {
                java.util.Date datar = getDateParse(data);
                FvaDataRetorno = getDateParse(datar, FORMAT_DATA);
            }
        } catch (Exception e) {

        }
        return FvaDataRetorno;
    }

    public static String getDateConvertFormat(String data, String patternr) throws ParseException {
        String FvaDataRetorno = "";
        try {
            if (isDate(data)) {
                java.util.Date datar = getDateParse(data);
                FvaDataRetorno = getDateParse(datar, patternr);
            }
        } catch (Exception e) {

        }
        return FvaDataRetorno;
    }

    public static String getDateConvertFormat(String data, String pattern, String patternr) throws ParseException {
        String FvaDataRetorno = "";
        try {
            if (isDate(data, pattern)) {
                java.util.Date datar = getDateParse(data, pattern);
                FvaDataRetorno = getDateParse(datar, patternr);
            }
        } catch (Exception e) {

        }
        return FvaDataRetorno;
    }

    public static String getDate(java.util.Date date) {
        String FvaRetorno = "";
        if (date == null) {
            return "";
        }
        try {
            DateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
            df.setTimeZone(TimeZone.getTimeZone("GMT -03:00"));
            df.setLenient(false);
            FvaRetorno = df.format(date);
        } catch (Exception e) {
            //System.out.println("Ocorreu no método getDate(java.sql.date) ao tentar formatar a data informada. Erro: " + e);
//            e.printStackTrace();
        }
        return FvaRetorno;
    }

    public static java.sql.Date getDateSQL(String dateStr) throws ParseException {
        java.sql.Date FvaRetorno = null;
        dateStr = dateStr + "";
        if (dateStr.equalsIgnoreCase("null") || dateStr.length() == 0) {
            return null;
        }
        try {
            java.util.Date newDate;
            DateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
            df.setLenient(false);
            newDate = df.parse(dateStr);

            FvaRetorno = new java.sql.Date(newDate.getTime());
        } catch (Exception e) {
            // System.out.println(
            //         "Ocorreu no método getDateSQL(String dateStr) "
            //         + "ao tentar formatar a data informada. Erro: " + e);
//            e.printStackTrace();
        }
        return FvaRetorno;
    }

    public static java.sql.Date getDateSQL(String dateStr, String formato) throws ParseException {
        java.sql.Date FvaRetorno = null;
        dateStr = dateStr + "";
        if (dateStr.equalsIgnoreCase("null") || dateStr.length() == 0) {
            return null;
        }
        try {

            java.util.Date newDate;
            DateFormat df = new SimpleDateFormat(formato);
            df.setLenient(false);
            newDate = df.parse(dateStr);

            FvaRetorno = new java.sql.Date(newDate.getTime());

        } catch (Exception e) {
            // System.out.println(
            //         "Ocorreu no método getDateSQL(String dateStr) "
            //         + "ao tentar formatar a data informada. Erro: " + e);
            e.printStackTrace();
        }
        return FvaRetorno;
    }

    public static java.sql.Date getDateSQL(java.util.Date newDate) {
        java.sql.Date FvaRetorno = null;

        if (newDate == null) {
            return null;
        }

        try {

            FvaRetorno = new java.sql.Date(newDate.getTime());

        } catch (Exception e) {
        }
        return FvaRetorno;
    }

    public static java.util.Date getDateUtil(String dateStr) throws ParseException {
        java.util.Date FvaRetorno = null;
        dateStr = dateStr + "";
        if (dateStr.equalsIgnoreCase("null") || dateStr.length() == 0) {
            return null;
        }
        try {
            java.util.Date newDate;
            DateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
            df.setLenient(false);
            newDate = df.parse(dateStr);

            FvaRetorno = new java.util.Date(newDate.getTime());
        } catch (Exception e) {
            // System.out.println("Ocorreu no método getDateSQL(String dateStr) ao tentar formatar a data informada. Erro: " + e);
//            e.printStackTrace();
        }
        return FvaRetorno;
    }
    
      public static java.util.Date getDateUtilUSA(String dateStr) throws ParseException {
        java.util.Date FvaRetorno = null;
        dateStr = dateStr + "";
        if (dateStr.equalsIgnoreCase("null") || dateStr.length() == 0) {
            return null;
        }
        try {
            java.util.Date newDate;
            DateFormat df = new SimpleDateFormat(FORMAT_DATA);
            df.setLenient(false);
            newDate = df.parse(dateStr);

            FvaRetorno = new java.util.Date(newDate.getTime());
        } catch (Exception e) {
            // System.out.println("Ocorreu no método getDateSQL(String dateStr) ao tentar formatar a data informada. Erro: " + e);
//            e.printStackTrace();
        }
        return FvaRetorno;
    }

    public static java.sql.Date getDateSQL() throws ParseException {
        java.sql.Date FvaRetorno = null;
        String dateStr = "" + getDate();
        if (dateStr.equalsIgnoreCase("null") || dateStr.length() == 0) {
            return null;
        }
        try {
            java.util.Date newDate;
            DateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
            df.setLenient(false);
            newDate = df.parse(dateStr);

            FvaRetorno = new java.sql.Date(newDate.getTime());
        } catch (Exception e) {
            // System.out.println("Ocorreu no m�todo getDateSQL(String dateStr) ao tentar formatar a data informada. Erro: " + e);
//            e.printStackTrace();
        }
        return FvaRetorno;
    }

    /*public static java.util.Date getDateSQL(String dateStr){
     java.util.Date FvaRetorno=null;
     dateStr=dateStr+"";
     if(dateStr.equalsIgnoreCase("null") || dateStr.length()==0) return null;
     try{
     java.util.Date newDate;
     DateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
     df.setLenient(false);
     newDate=df.parse(dateStr);
            
     FvaRetorno=newDate;
     }catch(Exception e){
     System.out.println("Ocorreu no m�todo getDateSQL(String dateStr) ao tentar formatar a data informada. Erro: "+e);
     e.printStackTrace();
     }
     return FvaRetorno;
     }
   
     public static java.util.Date getDateSQL(){
     java.util.Date FvaRetorno=null;
     String dateStr=""+getDate();
     if(dateStr.equalsIgnoreCase("null") || dateStr.length()==0) return null;
     try{
     java.util.Date newDate;
     DateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
     df.setLenient(false);
     newDate=df.parse(dateStr);
            
     FvaRetorno=newDate;
     }catch(Exception e){
     System.out.println("Ocorreu no m�todo getDateSQL(String dateStr) ao tentar formatar a data informada. Erro: "+e);
     e.printStackTrace();
     }
     return FvaRetorno;
     }*/
    public static int getDayWeek(String dataStr) throws ParseException {
        int FvaRetorno = -1;
        try {
            if (isDate(dataStr)) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(getDateParse(dataStr));
                FvaRetorno = calendar.get(calendar.DAY_OF_WEEK);
            }
        } catch (Exception e) {
            // System.out.println("Ocorreu um erro ao tentar formatar a data para o formato long. Erro: " + e);
        }
        return FvaRetorno;
    }
//Fim da fun��o para converter para outro formato
//---------------------------------------------------------------------------------------------------------------------------------------------

//Fun��es de Hora do Sistema
//-------------------------------------------------------------------------------------------------------------------------------------------------
    public static String getHora() {
        String FvaRetorno = "";
        try {
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            df.setLenient(false);
            Calendar calendar = new GregorianCalendar();
            FvaRetorno = df.format(calendar.getTime());
        } catch (Exception e) {
            // System.out.println("Ocorreu um erro getHora(). Erro: " + e);
        }
        return FvaRetorno;
    }

    public static String getHora(String pattern) {
        String FvaRetorno = "";
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);
            Calendar calendar = new GregorianCalendar();
            FvaRetorno = df.format(calendar.getTime());
        } catch (Exception e) {
            // System.out.println("Ocorreu um erro getHora(String pattern). Erro: " + e);
        }
        return FvaRetorno;
    }

    public static String getHora(Time time) {
        String FvaRetorno = "";
        try {
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            df.setLenient(false);
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(time.getTime());
            FvaRetorno = df.format(calendar.getTime());
        } catch (Exception e) {
            // System.out.println("Ocorreu um erro getHora(). Erro: " + e);
        }
        return FvaRetorno;
    }

    public static Time getTime(String str) throws ParseException {
        Time retorno = null;
        try {

            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            Date data = formatador.parse(str);

            retorno = new Time(data.getTime());

        } catch (Exception e) {

        }
        return retorno;
    }

    public static String getHoraSenSeg(Time time) {
        String FvaRetorno = "";
        try {
            DateFormat df = new SimpleDateFormat("HH:mm");
            df.setLenient(false);
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(time.getTime());
            FvaRetorno = df.format(calendar.getTime());
        } catch (Exception e) {
            // System.out.println("Ocorreu um erro getHora(). Erro: " + e);
        }
        return FvaRetorno;
    }

//Fim Fun��es de Hora do Sistema
//-------------------------------------------------------------------------------------------------------------------------------------------------
//Fun��es para compara��o de datas
//-------------------------------------------------------------------------------------------------------------------------------------------------
    public static int isDateCompare(String data1, String pattern1, String data2, String pattern2) throws ParseException {
        int FvaRetorno = -2;
        try {
            if (isDate(data1, pattern1) && isDate(data2, pattern2)) {
                java.util.Date dateA = getDateParse(data1, pattern1);
                java.util.Date dateB = getDateParse(data2, pattern2);
                FvaRetorno = dateA.compareTo(dateB);
            }
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro ao tentar formatar a data para o formato long. Erro: " + e);
        }
        return FvaRetorno;
    }

    public static int isDateCompare(String data1, String data2) throws ParseException {
        int FvaRetorno = -2;
        try {
            if (isDate(data1, FORMAT_DATA_BR) && isDate(data2, FORMAT_DATA_BR)) {
                java.util.Date dateA = getDateParse(data1, FORMAT_DATA_BR);
                java.util.Date dateB = getDateParse(data2, FORMAT_DATA_BR);
                FvaRetorno = dateA.compareTo(dateB);
            }
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro ao tentar formatar a data para o formato long. Erro: " + e);
        }
        return FvaRetorno;
    }

    public static int isDateCompare(java.util.Date data1, java.util.Date data2) {
        int FvaRetorno = -2;
        try {
            FvaRetorno = data1.compareTo(data2);
        } catch (Exception e) {
            // System.out.println("Ocorreu um erro ao tentar formatar a data para o formato long. Erro: " + e);
        }
        return FvaRetorno;
    }

    public static int isHoraCompare(String hora1, String hora2) throws ParseException {
        int FvaRetorno = -2;
        try {
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            df.setLenient(false);
            java.util.Date date1 = df.parse(hora1);
            java.util.Date date2 = df.parse(hora2);
            FvaRetorno = date1.compareTo(date2);
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro ao tentar formatar a data para o formato long. Erro: " + e);
        }
        return FvaRetorno;
    }

//Fim das Fun��es para compara��o de datas
//-------------------------------------------------------------------------------------------------------------------------------------------------
    public static String getDate(java.sql.Date data, String formato) {
        String FvaDataRetorno = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat(formato);
            df.setLenient(false);
            FvaDataRetorno = df.format(data);
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro na fun��o getDateParse(String dataStr, String pattern). Erro: " + e);
        }
        return FvaDataRetorno;
    }

    public static String getDataExtenso(String dateStr, String pattern) throws ParseException {
        //EEEEEE ',' dd 'de' MMMM 'de' yyyy" ---> Data por extenso
        //EEEEEE ---> dia da semana por extenso
        //dd ---> número do dia no mês
        //MMMM ---> nome do mês por extenso
        //yyyy ---> número do ano
        if (pattern.isEmpty()) {
            pattern = "EEEEEE ',' dd 'de' MMMM 'de' yyyy";
        }
        String FvaRetorno = "";
        try {
            java.util.Date dataTemp = getDateParse(dateStr);
            SimpleDateFormat df = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
            df.setLenient(false);
            FvaRetorno = df.format(dataTemp);
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro no método dataExtenso(String dateStr, String formato). Erro: " + e);
        }
        return FvaRetorno;
    }

    public static String getDataExtenso(java.util.Date dateSql, String pattern) {
        //EEEEEE ',' dd 'de' MMMM 'de' yyyy" ---> Data por extenso
        //EEEEEE ---> dia da semana por extenso
        //dd ---> número do dia no mês
        //MMMM ---> nome do mês por extenso
        //yyyy ---> número do ano
        if (pattern.isEmpty()) {
            pattern = "EEEEEE ',' dd 'de' MMMM 'de' yyyy";
        }
        String FvaRetorno = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
            df.setLenient(false);
            FvaRetorno = df.format(dateSql);
        } catch (Exception e) {
            // System.out.println("Ocorreu um erro no método dataExtenso(String dateStr, String formato). Erro: " + e);
        }
        return FvaRetorno;
    }

    public static String getDataExtenso(java.sql.Date dateSql, String pattern) {
        //EEEEEE ',' dd 'de' MMMM 'de' yyyy" ---> Data por extenso
        //EEEEEE ---> dia da semana por extenso
        //dd ---> número do dia no mês
        //MMMM ---> nome do mês por extenso
        //yyyy ---> número do ano
        if (pattern.isEmpty()) {
            pattern = "EEEEEE ',' dd 'de' MMMM 'de' yyyy";
        }
        String FvaRetorno = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
            df.setLenient(false);
            FvaRetorno = df.format(new Date(dateSql.getTime()));
        } catch (Exception e) {
            //System.out.println("Ocorreu um erro no método dataExtenso(String dateStr, String formato). Erro: " + e);
        }
        return FvaRetorno;
    }

    /**
     * Se formArq = true Formata de aaaammdd para aaaa-mm-dd; Senão Formata de
     * ddmmaaaa para aaaa-mm-dd;
     */
    public static String getDataFormatadaAB(String data, boolean formArq) {
        try {
            data = data.trim(); //aaaammdd
            String d, m, a;
            if (formArq) {
                a = data.substring(0, 4);//aaaammdd
                m = data.substring(4, 6);
                d = data.substring(6, 8);
            } else {
                d = data.substring(0, 2);//ddmmaaaa
                m = data.substring(2, 4);
                a = data.substring(4, 8);
            }
            return a + "-" + m + "-" + d;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * If formArq = true then get date on formate aaaammdd and return dd/mm/aaaa
     * else get date on formate ddmmaaaa and return dd/mm/aaaa
     */
    public static String getDataFormatadaDMA(String data, boolean formArq) {
        try {
            data = data.trim(); //aaaammdd
            String d, m, a;
            if (formArq) {
                a = data.substring(0, 4);//aaaammdd
                m = data.substring(4, 6);
                d = data.substring(6, 8);
            } else {
                d = data.substring(0, 2);//ddmmaaaa
                m = data.substring(2, 4);
                a = data.substring(4, 8);
            }
            return d + "/" + m + "/" + a;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Formata de aaaa-mm-dd para dd/mm/aaaa;
     */
    public static String getDataFormatadaF(String data) {
        try {//1111-22-33
            //33/22/1111
            data = data.trim();
            String d, m, a;
            a = data.substring(0, 4);
            m = data.substring(5, 7);
            //m = data.substring(4, 6);
            d = data.substring(8, 10);
            //d = data.substring(6, 8);
            //JOptionPane.showMessageDialog(null,d +"/"+ m +"/"+ a );        
            return d + "/" + m + "/" + a;
        } catch (Exception e) {
            return "  /  /    ";
        }
    }

    public static String getDataFormatadaF2(String data) {
        try {//1111-22-33
            //33/22/1111
            data = data.trim();
            String d, m, a;
            d = data.substring(0, 2);
            m = data.substring(3, 5);
            //m = data.substring(4, 6);
            a = data.substring(6, 10);
            //d = data.substring(6, 8);
            //JOptionPane.showMessageDialog(null,d +"/"+ m +"/"+ a );        
            return a + "-" + m + "-" + d;
        } catch (Exception e) {
            return "  -  -    ";
        }
    }

    public static void validarDataCadastroBancoXLancamento(java.util.Date dataBanco, java.util.Date dataLancamento) {
        if (dataBanco == null || dataLancamento == null) {
            throw new AltException("A data de lançamento ou data de cadastro do banco esta nula.");
        }

        int r = isDateCompare(dataLancamento, dataBanco);//DataLancamento menor que data banco
        if (r < 0) {
            throw new AltException("A data de lançamento(" + getDate(dataLancamento) + ") e menor que a data de cadastro(" + getDate(dataBanco) + ") do banco");
        }
    }

    public static void validarDataEncerramentoBancoXLancamento(java.util.Date dataEnceramento, java.util.Date dataLancamento) {
        if (dataEnceramento != null) {

            if (dataLancamento == null) {
                throw new AltException("A data de lançamento esta nula.");
            }

            int mesE = getMes(dataEnceramento);
            int mesL = getMes(dataLancamento);

            int r = isDateCompare(dataEnceramento, dataLancamento);//DataLancamento menor que data banco
            if (r <= 0) {
                throw new AltException("A data de encerramento(" + getDate(dataEnceramento) + ") do banco e menor ou igual a data de lançamento (" + getDate(dataLancamento) + ").");
            }

            if (mesL >= mesE) {
                throw new AltException("O banco esta encerrado não e permitido realizar movimentação.");
            }
        }
    }

    /**
     * Função para pegar a diferença de dias de uma data para outra.
     *
     * @param dataInicial Data Inicial
     * @param dataFinal Data Finaç
     * @return Inteiro, quantidade de dias
     *
     * Dá erro ao calcular do mes de setembro a outubro 23/09/2016 a 22/092016
     *
     */
    public static Integer _getQtdeDias(Date dataInicial, Date dataFinal) {
        if (dataInicial == null || dataFinal == null) {
            return 0;
        }
        if (dataFinal.compareTo(dataInicial) < 0) {
            return 0;
        }

        long milisecInicial = dataInicial.getTime();
        long milisecFinal = dataFinal.getTime();
        long dif = milisecFinal - milisecInicial;

        return (int) (((dif / 1000) / 60) / 60) / 24;
    }

    /**
     * Método para comparar as das e retornar o numero de dias de diferença
     * entre elas
     *
     * Compare two date and return the difference between them in days.
     *
     * @param dataLow The lowest date
     * @param dataHigh The highest date
     *
     * @return int
     */
    public static int getQtdeDias(java.util.Date dataLow, java.util.Date dataHigh) {
        GregorianCalendar startTime = new GregorianCalendar();
        GregorianCalendar endTime = new GregorianCalendar();
        GregorianCalendar curTime = new GregorianCalendar();
        GregorianCalendar baseTime = new GregorianCalendar();
        startTime.setTime(dataLow);
        endTime.setTime(dataHigh);
        int dif_multiplier = 1;
        // Verifica a ordem de inicio das datas
        if (dataLow.compareTo(dataHigh) < 0) {
            baseTime.setTime(dataHigh);
            curTime.setTime(dataLow);
            dif_multiplier = 1;
        } else {
            baseTime.setTime(dataLow);
            curTime.setTime(dataHigh);
            dif_multiplier = -1;
        }
        int result_years = 0;
        int result_months = 0;
        int result_days = 0;
        // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando
        // no total de dias. Ja leva em consideracao ano bissesto
        while (curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR)
                || curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)) {
            int max_day = curTime.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            result_months += max_day;
            curTime.add(GregorianCalendar.MONTH, 1);
        }
        // Marca que é um saldo negativo ou positivo
        result_months = result_months * dif_multiplier;
        // Retirna a diferenca de dias do total dos meses
        result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));
        return result_years + result_months + result_days;
    }

    /**
     * Função que retorna o dia de uma data
     *
     * @param data
     * @return inteiro
     */
    public static int getDia(java.util.Date data) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeZone(TimeZone.getTimeZone("GMT -03:00"));
        gc.setTimeInMillis(data.getTime());

        return gc.get(GregorianCalendar.DAY_OF_MONTH);
    }

    /**
     * Função que retorna o mes de uma data
     *
     * @param data
     * @return inteiro
     */
    public static int getMes(java.util.Date data) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeZone(TimeZone.getTimeZone("GMT -03:00"));
        gc.setTimeInMillis(data.getTime());

        return gc.get(GregorianCalendar.MONTH) + 1;
    }

    /**
     * Função que retorna o ano de uma data
     *
     * @param data
     * @return inteiro
     */
    public static int getAno(java.util.Date data) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeZone(TimeZone.getTimeZone("GMT -03:00"));
        gc.setTimeInMillis(data.getTime());

        return gc.get(GregorianCalendar.YEAR);
    }

    /**
     * Função que retorna a hora de uma data
     *
     * @param data
     * @return inteiro
     */
    public static String getHora(java.util.Date data) {
        GregorianCalendar gc = new GregorianCalendar();
        ///gc.setTimeZone(TimeZone.getTimeZone("GMT -02:00"));
        gc.setTimeInMillis(data.getTime());

        return gc.get(GregorianCalendar.HOUR_OF_DAY) + ":"
                + AuxString.completeAEsquerda("" + gc.get(GregorianCalendar.MINUTE), 2, "0") + ":"
                + AuxString.completeAEsquerda("" + gc.get(GregorianCalendar.SECOND), 2, "0");
    }

    public static Integer getQtdeMinutos(Date dataInicial, Date dataFinal) {
        if (dataFinal.compareTo(dataInicial) < 0) {
            return 0;
        }

        long milisecInicial = dataInicial.getTime();
        long milisecFinal = dataFinal.getTime();
        long dif = milisecFinal - milisecInicial;
        //System.out.println("1: " + milisecInicial);
        //System.out.println("2: " + milisecFinal);
        //System.out.println("3: " + dif);
        int ret = (int) (((dif / 1000) / 60));
        return ret;
    }

    /**
     * Função que retorna a hora de uma data
     *
     * @param data
     * @return inteiro
     */
    public static String getHoraSemFormato(java.util.Date data) {
        GregorianCalendar gc = new GregorianCalendar();
        ///gc.setTimeZone(TimeZone.getTimeZone("GMT -02:00"));
        gc.setTimeInMillis(data.getTime());

        return gc.get(GregorianCalendar.HOUR_OF_DAY)
                + AuxString.completeAEsquerda("" + gc.get(GregorianCalendar.MINUTE), 2, "0")
                + AuxString.completeAEsquerda("" + gc.get(GregorianCalendar.SECOND), 2, "0");
    }
    
    /**
     * Retorna o ultimo dia do mes indicado 1 = Janeiro e 12 = Dezembro
     */
    public static int getUltimoDia(int mes, int ano) {
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return (new GregorianCalendar().isLeapYear(ano) ? 29 : 28);
        }
        return 30;
    }    
    public static Integer getUltimoDiaDoMes(Date data) {
    	Calendar c = Calendar.getInstance();
        c.setTime(data);
        int i = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return i;
    }
    public static Integer getIdade(Date dataNascimento) {

      	Calendar hoje = Calendar.getInstance();
        
        Calendar nasc = Calendar.getInstance();
        nasc.setTime(dataNascimento);
        
        Calendar idade = hoje;
        idade.add(Calendar.YEAR, -nasc.get(Calendar.YEAR));
        idade.add(Calendar.MONTH, -nasc.get(Calendar.MONTH));
        idade.add(Calendar.DAY_OF_MONTH, -nasc.get(Calendar.DAY_OF_MONTH));
        
        
        int i = idade.get(Calendar.YEAR);
        return i;
    }

    public static Integer getDiasCorridos(Date dataInicio, Integer diasUteis) {

        Calendar inicio = Calendar.getInstance();
        inicio.setTime(dataInicio);
        
        int cont = 0;
        int diasCorridos=0;
        
        if(diasUteis == 0)
            return 0;
        
        while (cont < diasUteis) {
            System.out.println("data: "+inicio);
            if(inicio.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || inicio.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                System.out.println("sabado ou domingo");
            }else{
                cont ++;
            }
            
            diasCorridos++;
            inicio.roll(Calendar.DAY_OF_MONTH, 1);
            System.out.println("diascorridos: "+diasCorridos);
            System.out.println("cont: "+cont);
        }
        
        return diasCorridos;
    }
    public static int getIntervaloEmDias(String dataInit, String dataFinish) {
        int[] data1 = new int[3];
        int[] data2 = new int[3];
        int dias = 0;

        //Desmembra dia, mes e ano das datas;  
        data1[0] = Integer.parseInt(dataInit.substring(0, 2)); //dia;  
        data1[1] = Integer.parseInt(dataInit.substring(3, 5)); //mes;  
        data1[2] = Integer.parseInt(dataInit.substring(6, 10)); //ano;  

        data2[0] = Integer.parseInt(dataFinish.substring(0, 2)); //dia;  
        data2[1] = Integer.parseInt(dataFinish.substring(3, 5)); //mes;  
        data2[2] = Integer.parseInt(dataFinish.substring(6, 10)); //ano;  

        int m1 = getDiasDoMes(data1[1], data1[2]);
        int m2 = getDiasDoMes(data2[1], data2[2]);

        if (data2[2] == data1[2]) { // Calculo para o mesmo ano;  
            if (data1[1] == data2[1]) {//Calculo para o mesmo ano e mesmo mes
                dias = data2[0] - data1[0];
            } else {
                int diasMes1 = (m1 - data1[0]) + 1;     // numero de dias do primeiro mês;  
                int diasMes2 = m2 - (m2 - data2[0]);    // numero de dias do ultimo mês  
                int interMeses = (data2[1] - data1[1]); // quantidade de meses entre o primeiro e o ultimo mes; Valor 1 ou 0 não tem mês entre o primeiro e o ultimo mês; 

                if (interMeses == 0) {
                    dias = diasMes1 + diasMes2;
                } else {
                    for (int i = 1; i < interMeses; i++) {
                        dias += getDiasDoMes(data1[1] + i, data1[2]);//somas os dias dos meses entre o primeiro e o ultimo mes;
                    }
                    dias += (diasMes1 + diasMes2);
                }
            }
        } else {//Calculo para anos diferentes  
            
            //Se a data inicial for maior q a final, retorma zero
            if(data1[2] > data2[2])
                return 0;
            
            int mesesAno1 = 12 - data1[1];      //Numero de Meses ate o fim do ano (O mês da data não está incluído);  
            int diasMes1 = m1 - data1[0];       //Numero de dias do mês inicial;  
            for (int i = 1; i <= mesesAno1; i++) {
                dias += getDiasDoMes(data1[1] + i, data1[2]);//Soma dos dias dos meses que falta pra acabar o ano; Não entra aqui os dias do mes atual
            }
            dias += diasMes1;

            int diasMes2 = m2 - (m2 - data2[0]);  //Numero de dias do ultimo mês  
            int mesesAno2 = data2[1] - 1;         //Numero de meses do ultimo ano;  
            for (int i = 1; i <= mesesAno2; i++) {
                dias += getDiasDoMes(i, data2[2]);//Soma dos dias dos meses do ultimo ano; Não entra aqui os dias do ultimo mes
            }
            dias += diasMes2;

            int anoIni = data1[2];
            int anoFin = data2[2];
            int anoAux = anoIni + 1;

            while (anoAux < anoFin) {//Somas os anos inteiros desde que seja maior que ano inicial e menor que ano final
                if (anoIsBisexto(anoAux)) {
                    dias += 366;
                } else {
                    dias += 365;
                }

                anoAux += 1;
            }

        }
        return dias;
    }
    public static int getDiasDoMes(int month, int year) {
        //O ano valor do ano será usado para verificar se o ano é bisexto  
        int[] mes = new int[12];

        mes[0] = 31;

        if (anoIsBisexto(year)) {
            mes[1] = 29;
        } else {
            mes[1] = 28;
        }

        mes[2] = 31;

        mes[3] = 30;

        mes[4] = 31;

        mes[5] = 30;

        mes[6] = 31;

        mes[7] = 31;

        mes[8] = 30;

        mes[9] = 31;

        mes[10] = 30;

        mes[11] = 31;

        return mes[month - 1];
    }
public static boolean anoIsBisexto(int year) {
////        //Ano Bisexto (Regras do Calendário Gregoriano):  
////        // 1 - Todo ano divisível por 4 é bissexto  
////        // 2 - Todo ano divisível por 100 não é bissexto  
////        // 3 - Mas se o ano for também divisível por 400 é bissexto  
////        boolean retorno;
////        if (year % 4 == 0) {
////            if (year % 100 == 0) {
////                retorno = year % 400 == 0;
////            } else {
////                retorno = true;
////            }
////        } else {
////            retorno = false;
////        }
////        return retorno;

    return Year.isLeap(year);
    }
}
