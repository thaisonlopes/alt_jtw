package org.primefaces.util.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import java.time.LocalDate;
import java.sql.Time;
import java.time.ZoneId;
import java.util.Calendar;

public class DataUtil {

    public static String FORMAT_DATA = "yyyy-MM-dd"; // Formato de data do MySQL
    public static String FORMAT_DATA_BR = "dd/MM/yyyy";
    public static String FORMATO_FUNCAO_DATA = "NOW();"; // GETDATE()
    public static String FORMAT_DATATIME_BR = "dd/MM/yyyy HH:mm:ss";
    public static String FORMAT_DATATIME_USA = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_DATATIME_USA2 = "yyyy-MM-dd HH:mm";

    public static final int FORMATO_RECUPERADO = 1;
    public static final int FORMATO_BANCO_DE_DADOS = 2;
    public static final int NAO_E_FORMATO_DE_DATA = 3;

    public static String getIntervaloNoMes(int mesIn, int mesFin, int ano) {
        String mIn = mesIn >= 10 ? "" + mesIn : "0" + mesIn;

        String mFin = mesFin >= 10 ? "" + mesFin : "0" + mesFin;

        int dFin = getNumeroDeDiasNoMes(mesFin, ano);

        return "01/" + mIn + "/" + ano + "  a  " + dFin + "/" + mFin + "/" + ano;
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

        }
        return FvaRetorno;
    }

    public static String getDataAtual() {
        try {

            DateFormat df = new SimpleDateFormat(FORMAT_DATA);

            java.util.Date date = new java.util.Date();

            return df.format(date);

        } catch (Exception e) {

        }
        return null;
    }

    public static String getDataAtual(String pattern) {
        try {

            DateFormat df = new SimpleDateFormat(pattern);

            java.util.Date date = new java.util.Date();

            return df.format(date);

        } catch (Exception e) {

        }

        return null;

    }

    public static String getDateTime() {
        try {

            DateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);

            java.util.Date date = new java.util.Date();

            return df.format(date);

        } catch (Exception e) {

        }
        return null;
    }

    public static String getDateTime(java.sql.Timestamp date) {

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

        }

        return FvaRetorno;

    }

    public static String getDateTime(java.util.Date date) {

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

        }

        return FvaDataRetorno;

    }

    public static String getDataHoraUS() {

        String FvaDataRetorno = "";

        try {

            SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATATIME_USA2);
            df.setLenient(false);

            FvaDataRetorno = df.format(new java.util.Date());

        } catch (Exception e) {

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

        }

        return FvaDataRetorno;

    }

    public static String getDate() {

        String FvaDataRetorno = "";

        try {

            java.util.Date data = new java.util.Date();
            FvaDataRetorno = getDateParse(data);

        } catch (Exception e) {

        }

        return FvaDataRetorno;

    }

    public static java.util.Date getDataUtil() {

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

    public static String getDate(java.util.Date date) {

        String FvaRetorno = "";

        if (date == null) {
            return "";
        }

        try {

            DateFormat df = new SimpleDateFormat(FORMAT_DATA_BR);
            df.setTimeZone(java.util.TimeZone.getTimeZone("GMT -03:00"));
            df.setLenient(false);

            FvaRetorno = df.format(date);

        } catch (Exception e) {

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

        }

        return FvaRetorno;

    }

    public static String getDate(java.sql.Date data, String formato) {

        String FvaDataRetorno = "";

        try {

            SimpleDateFormat df = new SimpleDateFormat(formato);
            df.setLenient(false);

            FvaDataRetorno = df.format(data);

        } catch (Exception e) {

        }

        return FvaDataRetorno;

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

    // EEEEEE ',' dd 'de' MMMM 'de' yyyy" ---> Data por extenso
    // EEEEEE ---> dia da semana por extenso
    // dd ---> número do dia no mês
    // MMMM ---> nome do mês por extenso
    // yyyy ---> número do ano
    public static String getDataExtenso(String dateStr, String pattern) throws ParseException {

        if (pattern.isEmpty()) {
            pattern = "EEEEEE ',' dd 'de' MMMM 'de' yyyy";
        }

        String FvaRetorno = "";

        try {

            java.util.Date dataTemp = getDateParse(dateStr);

            SimpleDateFormat df = new SimpleDateFormat(pattern, new java.util.Locale("pt", "BR"));
            df.setLenient(false);

            FvaRetorno = df.format(dataTemp);

        } catch (Exception e) {

        }

        return FvaRetorno;

    }

    public static String getDataExtenso(java.util.Date dateSql, String pattern) {

        if (pattern.isEmpty()) {
            pattern = "EEEEEE ',' dd 'de' MMMM 'de' yyyy";
        }

        String FvaRetorno = "";

        try {

            SimpleDateFormat df = new SimpleDateFormat(pattern, new java.util.Locale("pt", "BR"));
            df.setLenient(false);

            FvaRetorno = df.format(dateSql);

        } catch (Exception e) {

        }

        return FvaRetorno;

    }

    public static String getDataExtenso(java.sql.Date dateSql, String pattern) {

        if (pattern.isEmpty()) {
            pattern = "EEEEEE ',' dd 'de' MMMM 'de' yyyy";
        }

        String FvaRetorno = "";

        try {

            SimpleDateFormat df = new SimpleDateFormat(pattern, new java.util.Locale("pt", "BR"));
            df.setLenient(false);

            FvaRetorno = df.format(new java.util.Date(dateSql.getTime()));

        } catch (Exception e) {

        }

        return FvaRetorno;

    }

    // -----------------------------------------------------------------------------------------------------------------------
    @SuppressWarnings("deprecation")
    public static java.sql.Time getTime(String time) {
        java.sql.Time ret = new java.sql.Time(getHora(time), getMin(time), getSeg(time));
        return ret;
    }

    private static int getHora(String time) {
        return getTimeArray(time)[0];
    }

    private static int getMin(String time) {
        return getTimeArray(time)[1];
    }

    private static int getSeg(String time) {
        int array[] = getTimeArray(time);
        return array.length > 2 ? array[2] : 0;
    }

    private static int[] getTimeArray(String time) {
        java.util.StringTokenizer st = new java.util.StringTokenizer(time, ":");

        int ret[] = new int[st.countTokens()];

        int cont = 0;
        while (st.hasMoreElements()) {
            ret[cont] = Integer.parseInt(st.nextToken());
            cont++;
        }

        return ret;
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

    public static String recupDatas(java.sql.Date data) throws ParseException {
        String ret = Data.getDateConvertFormat(getDate(data), FORMAT_DATA_BR);

        return ret;
    }

    public static String recupDatas(String data) {

        if (data == null) {
            return "";
        }
        if ((data.length() != 10)) {
            return "";
        }
        if (data.substring(0, 4).trim().equals("0000")) {
            return "";
        }
        if (data.substring(0, 1).trim().equals("")) {
            return "";
        }

        int formato = getFormatoDaData(data);

        if (formato == NAO_E_FORMATO_DE_DATA) {
            return "";
        } else if (formato == FORMATO_RECUPERADO) {
            return data;
        }

        java.util.StringTokenizer st = new java.util.StringTokenizer(data, "-");
        String a = st.nextToken();
        String m = st.nextToken();
        String d = st.nextToken();

        String ret = d + "/" + m + "/" + a;

        return ret;
    }

    public static int getFormatoDaData(String data) {

        if (data == null) {
            return NAO_E_FORMATO_DE_DATA;
        }
        if (data.length() != 10) {
            return NAO_E_FORMATO_DE_DATA;
        }

        java.util.StringTokenizer st = new java.util.StringTokenizer(data, "-");
        java.util.StringTokenizer st1 = new java.util.StringTokenizer(data, "/");

        if (st.countTokens() == 3) {
            if (!data.substring(4, 5).equals("-")) {
                return NAO_E_FORMATO_DE_DATA;
            }
            if (!data.substring(7, 8).equals("-")) {
                return NAO_E_FORMATO_DE_DATA;
            }
            return FORMATO_BANCO_DE_DADOS;
        }

        if (st1.countTokens() == 3) {
            if (!data.substring(2, 3).equals("/")) {
                return NAO_E_FORMATO_DE_DATA;
            }
            if (!data.substring(5, 6).equals("/")) {
                return NAO_E_FORMATO_DE_DATA;
            }
            return FORMATO_RECUPERADO;
        }

        return NAO_E_FORMATO_DE_DATA;
    }

    /*
     * Retorna uma data no formato ##/##/#### com o mês e o ano passado como parâmetro e o dia 01
     */
    public static java.sql.Date getData1DeJaneiro(int ano) throws ParseException {
        return getDateSQL(getDataDiaInicialDoMes(1, ano));
    }

    /*
     * Retorna uma data no formato ##/##/#### com o mês e o ano passado como parâmetro e o dia 01
     */
    public static String getDataDiaInicialDoMes(int mes, int ano) {
        return "01" + "/" + TextoUtil.completaAEsquerda(mes + "", 2, "0") + "/" + ano;
    }

    /*
     * Retorna uma data no formato ##/##/#### com o mês e o ano da data passada como parâmetro e o dia 01
     */
    public static String getDataDiaInicialDoMes(String data) throws ParseException {
        int mes = getMes(getDateSQL(data));
        int ano = getAno(getDateSQL(data));

        return getDataDiaInicialDoMes(mes, ano);
    }

    public static java.sql.Date getDataMinima() throws ParseException {
        return getDateSQL("01/01/1980");
    }

    public static java.sql.Date getDataMaxima() throws ParseException {
        return getDateSQL("31/12/2099");
    }

    /**
     * Devolve true se data1 é de um dia inferior a data2 ou false caso
     * contrário
     *
     * @param data1
     * @param data2
     * @return
     */
    public static boolean eDiaMenor(java.util.Date data1, java.util.Date data2) {
        java.util.Date dataPagto = data1;
        java.util.Date dataLanc = data2;

        int dp = getDia(dataPagto);
        int mp = getMes(dataPagto);
        int ap = getAno(dataPagto);

        int dl = getDia(dataLanc);
        int ml = getMes(dataLanc);
        int al = getAno(dataLanc);

        if (ap < al) {
            return true;
        } else if (ap > al) {
            return false;
        } else if (mp < ml) {
            return true;
        } else if (mp > ml) {
            return false;
        } else if (dp < dl) {
            return true;
        }
        return false;
    }

    /**
     * Devolve true se data1 é do mesmo dia de data2 ou false caso contrário
     *
     * @param data1
     * @param data2
     * @return
     */
    public static boolean eDiaIgual(java.util.Date data1, java.util.Date data2) {
        if (!eDiaMenor(data1, data2) && !eDiaMenor(data2, data1)) {
            return true;
        }
        return false;
    }

    /**
     * Devolve true se data1 é maior que a data2 ou false caso contrário
     *
     * @param data1
     * @param data2
     * @return
     */
    public static boolean eDiaMaior(java.util.Date data1, java.util.Date data2) {
        if (!eDiaMenor(data1, data2) && !eDiaIgual(data1, data2)) {
            return true;
        }
        return false;
    }

    public static java.sql.Date getUltimaDataAnoOrca(int anoOc) throws ParseException {
        String dt = "31/12/" + anoOc;

        return getDateSQL(dt);
    }

    public static java.sql.Date getDataAtualSql() {
        java.util.Date data = new java.util.Date();

        java.sql.Date d = new java.sql.Date(data.getTime());

        return d;
    }

    public static String getMesPorExtenso(int mes) {
        String retorno = "";

        switch (mes) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
        }
        return retorno;
    }

    public static String getMesPorExtenso_Com13(int mes) {
        String retorno = "";

        switch (mes) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
            case 13:
                return "Encerramento";
        }
        return retorno;
    }

    public static int getAno(java.util.Date data) {
        java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
        gc.setTimeZone(java.util.TimeZone.getTimeZone("GMT -03:00"));
        gc.setTimeInMillis(data.getTime());

        return gc.get(java.util.GregorianCalendar.YEAR);
    }

    public static int getMes(java.util.Date data) {
        java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
        gc.setTimeZone(java.util.TimeZone.getTimeZone("GMT -03:00"));
        gc.setTimeInMillis(data.getTime());

        return gc.get(java.util.GregorianCalendar.MONTH) + 1;
    }

    public static int getDia(java.util.Date data) {
        java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
        gc.setTimeZone(java.util.TimeZone.getTimeZone("GMT -03:00"));
        gc.setTimeInMillis(data.getTime());

        return gc.get(java.util.GregorianCalendar.DAY_OF_MONTH);
    }

    public static int getAno(String data) throws ParseException {
        java.sql.Date dt = recupDataDate(data);
        if (dt == null) {
            return 0;
        }
        return getAno(dt);
    }

    public static int getMes(String data) throws ParseException {
        java.sql.Date dt = recupDataDate(data);
        if (dt == null) {
            return 0;
        }
        return getMes(dt);
    }

    public static int getDia(String data) throws ParseException {
        java.sql.Date dt = recupDataDate(data);
        if (dt == null) {
            return 0;
        }
        return getDia(dt);
    }

    public static java.sql.Date recupDataDate(String data) throws ParseException {
        return getDateSQL(recupDatas(data));
    }

    public static String getDataPorExtenso(java.util.Date data) {
        return getDia(data) + " de " + getMesPorExtenso(getMes(data)) + " de " + getAno(data);
    }

    public static java.sql.Date getData(int dia, int mes, int ano) throws ParseException {
        String d = TextoUtil.completaAEsquerda("" + dia, 2, "0");
        String m = TextoUtil.completaAEsquerda("" + mes, 2, "0");
        String a = "" + ano;

        String dt = d + "/" + m + "/" + a;

        return getDateSQL(dt);
    }

    public static String getDiaDaSemana(int dia, int mes, int ano) throws ParseException {
        int dow = getDiaDaSemanaInt(dia, mes, ano);

        if (dow == java.util.GregorianCalendar.SUNDAY) {
            return "Domingo";
        } else if (dow == java.util.GregorianCalendar.MONDAY) {
            return "Segunda Feira";
        } else if (dow == java.util.GregorianCalendar.TUESDAY) {
            return "Terça Feira";
        } else if (dow == java.util.GregorianCalendar.WEDNESDAY) {
            return "Quarta Feira";
        } else if (dow == java.util.GregorianCalendar.THURSDAY) {
            return "Quinta Feira";
        } else if (dow == java.util.GregorianCalendar.FRIDAY) {
            return "Sexta Feira";
        } else if (dow == java.util.GregorianCalendar.SATURDAY) {
            return "Sábado";
        } else {
            String msg = "Dia da Semana inválido(" + dow + ")";
            throw new AltException(msg);
        }
    }

    private static int getDiaDaSemanaInt(int dia, int mes, int ano) throws ParseException {
        java.util.Date data = getData(dia, mes, ano);

        java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
        gc.setTimeZone(java.util.TimeZone.getTimeZone("GMT -03:00"));
        gc.setTimeInMillis(data.getTime());

        int ret = gc.get(java.util.GregorianCalendar.DAY_OF_WEEK);

        return ret;
    }

    public static String getDataDiaFinalDoMes(int mes, int ano) {
        int numDias = getNumeroDeDiasNoMes(mes, ano);

        return TextoUtil.completaAEsquerda("" + numDias, 2, "0")
                + "/"
                + TextoUtil.completaAEsquerda(mes + "", 2, "0")
                + "/"
                + ano;
    }

    public static java.sql.Date getDataDiaFinalDoMesSQL(int mes, int ano) throws ParseException {
        return getDateSQL(getDataDiaFinalDoMes(mes, ano));
    }

    public static int getNumeroDeDiasNoMes(int mes, int ano) {
        mes--;
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(GregorianCalendar.DAY_OF_MONTH, 1);
        gc.set(GregorianCalendar.MONTH, mes);
        gc.set(GregorianCalendar.YEAR, ano);
        return gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    }

    public static boolean eSabadoOuDomingo(int dia, int mes, int ano) throws ParseException {
        int dsi = getDiaDaSemanaInt(dia, mes, ano);

        return !(dsi == java.util.GregorianCalendar.SATURDAY || dsi == java.util.GregorianCalendar.SUNDAY);
    }

    public static boolean eDiaUtil(int dia, int mes, int ano, boolean feriado) throws ParseException {
        boolean diaUtil = DataUtil.eSabadoOuDomingo(dia, mes, ano);
        diaUtil = diaUtil && (!feriado);

        return diaUtil;
    }

    public static void validarDataCadastroBancoXLancamento(java.util.Date dataBanco, java.util.Date dataLancamento) {
        if (dataBanco == null || dataLancamento == null) {
            throw new AltException("A data de lançamento ou data de cadastro do banco esta nula.");
        }

        int r = isDateCompare(dataLancamento, dataBanco);// DataLancamento menor que data banco
        if (r < 0) {
            throw new AltException("A data de lançamento(" + getDate(dataLancamento) + ") e menor que a data de cadastro("
                    + getDate(dataBanco) + ") do banco");
        }
    }

    /**
     * *
     * @param data1 Data usada como referência
     * @param data2 Data usado para se comparar com a referência
     *
     * @return
     *
     * -1 -> se data1 menor que data2<br>
     * 0 -> se data1 igual data2<br>
     * 1 -> se data1 maior que data2.<br>
     *
     */
    public static int isDateCompare(java.util.Date data1, java.util.Date data2) {
        int FvaRetorno = -2;
        try {

            FvaRetorno = data1.compareTo(data2);

        } catch (Exception e) {

        }
        return FvaRetorno;
    }

    public static String getDataDiaAnterior() throws ParseException {
        return Data.getDate(getDataDiaAnteriorSql());
    }

    public static java.sql.Date getDataDiaAnteriorSql() throws ParseException {
        java.sql.Date data = getDataAtualSql();

        return getDataDiaAnteriorSql(data);
    }

    public static java.sql.Date getDataDiaAnteriorSql(java.util.Date data) throws ParseException {
        int dia = getDia(data);

        int mes = getMes(data);

        int ano = getAno(data);

        if (dia == 1) {
            if (mes == 1) {
                String dts = preencheData(31, 12, (ano - 1));
                return getDateSQL(dts);
            } else {
                int numDias = getNumeroDeDiasNoMes(mes - 1, ano);

                String dts = preencheData(numDias, mes - 1, ano);
                return getDateSQL(dts);
            }
        }
        String dts = preencheData(dia - 1, mes, ano);
        return getDateSQL(dts);
    }

    private static String preencheData(int dia, int mes, int ano) {
        return TextoUtil.completaAEsquerda("" + dia, 2, "0") + "/" + TextoUtil.completaAEsquerda("" + mes, 2, "0") + "/" + ano;
    }

    public static boolean isDate(String dateStr) {
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
        }
        return FvaRetorno;
    }
    
    public static java.util.Date getData(LocalDate localDate) {
        
        try {
            
            return java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            
        } catch (Exception e) {
            return null;
        }
        
    }

}
