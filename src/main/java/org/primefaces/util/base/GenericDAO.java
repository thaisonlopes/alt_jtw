package org.primefaces.util.base;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.primefaces.apollo.entidades.log.Log;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.math.BigInteger;

/**
 *
 * @author Lidiney
 *
 */
public abstract class GenericDAO implements Serializable {

    private static final long serialVersionUID = 7428542967806300050L;

    private String tela;
    private String itemTela;

    private boolean logAble = true;

    public GenericDAO() {

    }

    protected abstract void setLog(Log log, Serializable pojo);

    // -------------------------------------------------------------------------------------------------------
    /**
     *
     * @param pojo
     * @param session
     */
    protected void validSave(Serializable pojo, EntityManager session) {

    }

    /**
     *
     * @param pojo
     * @param session
     */
    protected void validInsere(Serializable pojo, EntityManager session) {

    }

    /**
     *
     * @param pojo
     * @param session
     */
    protected void validAltera(Serializable pojo, EntityManager session) {

    }

    /**
     *
     * @param pojo
     * @param session
     */
    protected void validDelete(Serializable pojo, EntityManager session) {

    }

    // -------------------------------------------------------------------------------------------------------------
    /**
     *
     * @param e Exception
     * @param operacao
     */
    public void rollback(Exception e, String operacao) {

        if (e instanceof CadastroException) {
            e.printStackTrace();
            throw new AltException(e.getLocalizedMessage());

        } else {
            e.printStackTrace();
            throw new AltException(e.getLocalizedMessage());
        }

    }

    /**
     *
     * @param e Exception
     */
    public void rollbackProcessa(Exception e) {
        rollback(e, ConstAcaoLog.PROCESSAMENTO);
    }

    /**
     *
     * @param e Exception
     */
    public void rollbackSalva(Exception e) {
        rollback(e, ConstAcaoLog.SALVAMENTO);
    }

    /**
     *
     * @param e Exception
     */
    public void rollbackInsere(Exception e) {
        rollback(e, ConstAcaoLog.INSERCAO);
    }

    /**
     *
     * @param e Exception
     */
    public void rollbackAltera(Exception e) {
        rollback(e, ConstAcaoLog.ALTERACAO);
    }

    /**
     *
     * @param e Exception
     */
    public void rollbackDelete(Exception e) {
        rollback(e, ConstAcaoLog.EXCLUSAO);
    }

    /**
     *
     * @param e Exception
     */
    public void rollback(Exception e) {
        rollback(e, ConstAcaoLog.ACAO);
    }

    /**
     *
     * @param e Exception
     */
    public void rollbackPesquisa(Exception e) {
        rollback(e, ConstAcaoLog.PESQUISA);
    }

    // ------------------------------------------------------------------------------------------------------------------
    /**
     *
     * @param pojo
     * @param session
     * @param operacao
     */
    public void commitWrite(Serializable pojo, EntityManager session, String operacao) {

        try {

            if (isLogAble()) {
                session.persist(getLog(pojo, operacao));
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            throw new AltException(ex.getLocalizedMessage());

        }

    }

    /**
     *
     * @param session
     */
    public void commitProcessa(EntityManager session) {
        commitWrite(null, session, ConstAcaoLog.PROCESSAMENTO);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void commitSalva(Serializable pojo, EntityManager session) {
        commitWrite(pojo, session, ConstAcaoLog.SALVAMENTO);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void commitWrite(Serializable pojo, EntityManager session) {
        commitWrite(pojo, session, ConstAcaoLog.ACAO);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void commitInsere(Serializable pojo, EntityManager session) {
        commitWrite(pojo, session, ConstAcaoLog.INSERCAO);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void commitAltera(Serializable pojo, EntityManager session) {
        commitWrite(pojo, session, ConstAcaoLog.ALTERACAO);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void commitDelete(Serializable pojo, EntityManager session) {
        commitWrite(pojo, session, ConstAcaoLog.EXCLUSAO);
    }

    //-----------------------logs-----------------------------------------------
    /**
     *
     * @param session
     */
    public void logProcessa(EntityManager session) {
        commitWrite(null, session, ConstAcaoLog.PROCESSAMENTO);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void logSalva(Serializable pojo, EntityManager session) {
        commitWrite(pojo, session, ConstAcaoLog.SALVAMENTO);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void logWrite(Serializable pojo, EntityManager session) {
        commitWrite(pojo, session, ConstAcaoLog.ACAO);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void logInsere(Serializable pojo, EntityManager session) {
        commitWrite(pojo, session, ConstAcaoLog.INSERCAO);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void logAltera(Serializable pojo, EntityManager session) {
        commitWrite(pojo, session, ConstAcaoLog.ALTERACAO);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void logDelete(Serializable pojo, EntityManager session) {
        commitWrite(pojo, session, ConstAcaoLog.EXCLUSAO);
    }

    // -----------------------------------------------------------------------------------------------------
    private Log getLog(Serializable pojo, String operacao) {

        Log log = getNewLog();

        excecaoTelaNula();

        log.setAcao(operacao);
        log.setTela(getTela());

        if (pojo != null) {
            setLog(log, pojo);
        }

        return log;
    }

    private void excecaoTelaNula() {
        if (getTela() == null) {
            throw new AltException("Tela não informada.");
        }
    }

    private Log getNewLog() {
        Log log = new Log();

        return log;
    }

    public String getTela() {
        return tela;
    }

    public void setTela(String tela) {
        this.tela = tela;
    }

    public String getItemTela() {
        return itemTela;
    }

    public void setItemTela(String itemTela) {
        this.itemTela = itemTela;
    }

    public boolean isLogAble() {
        return logAble;
    }

    public void setLogAble(boolean logAble) {
        this.logAble = logAble;
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void inserePojo(Serializable pojo, EntityManager session) {
        validInsere(pojo, session);
        session.persist(pojo);
    }

    /**
     *
     * @param pojo
     * @param session
     * @param validaInsert
     */
    public void inserePojo(Serializable pojo, EntityManager session, boolean validaInsert) {
        if (validaInsert) {
            validInsere(pojo, session);
        }
        session.persist(pojo);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void savePojo(Serializable pojo, EntityManager session) {
        validSave(pojo, session);
        session.merge(pojo);
    }

    /**
     *
     * @param pojo
     * @param session
     * @param validaSalvar
     */
    public void savePojo(Serializable pojo, EntityManager session, boolean validaSalvar) {
        if (validaSalvar) {
            validSave(pojo, session);
        }
        session.merge(pojo);
    }

    /**
     * @param <T>
     * @param session
     * @param pojo
     * @return Entidade.
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T saveOrUpdatePojo(EntityManager session, Serializable pojo) {
        pojo = session.merge(pojo);
        return (T) pojo;
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void alteraPojo(Serializable pojo, EntityManager session) {
        validAltera(pojo, session);
        session.merge(pojo);
    }

    /**
     *
     * @param pojo
     * @param session
     * @param validaAlterar
     */
    public void alteraPojo(Serializable pojo, EntityManager session, boolean validaAlterar) {
        if (validaAlterar) {
            validAltera(pojo, session);
        }
        session.merge(pojo);
    }

    /**
     *
     * @param pojo
     * @param session
     */
    public void deletePojo(Serializable pojo, EntityManager session) {
        validDelete(pojo, session);
        session.remove(session.contains(pojo) ? pojo : session.merge(pojo));
    }

    /**
     *
     * @param pojo
     * @param session
     * @param validaDelete
     */
    public void deletePojo(Serializable pojo, EntityManager session, boolean validaDelete) {
        if (validaDelete) {
            validDelete(pojo, session);
        }
        session.remove(session.contains(pojo) ? pojo : session.merge(pojo));
    }

    /**
     * Pesquisar resultado unico
     *
     * @param <T>
     * @param session
     * @param classToSearch
     * @param query
     * @param parametros
     * @return Retorna o primeiro resultado da pesquisa se encontrado se não
     * retorna null.
     *
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T getPojoUnique(EntityManager session, Class<T> classToSearch, String query, Object... parametros) {

        Query qr = session.createQuery(query);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        List<T> results = qr.getResultList();
        if (results != null && !results.isEmpty()) {
            return (T) results.get(0);
        } else {
            return null;
        }

    }

    /**
     * Pesquisar resultado unico
     *
     * @param <T>
     * @param session
     * @param classToSearch
     * @param query
     * @param parametros
     * @return Retorna o primeiro resultado da pesquisa se encontrado se não
     * retorna null.
     *
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T getPojoUniqueNamed(EntityManager session, Class<T> classToSearch, String query, Object... parametros) {

        Query qr = session.createNamedQuery(query);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        List<T> results = qr.getResultList();
        if (results != null && !results.isEmpty()) {
            return (T) results.get(0);
        } else {
            return null;
        }

    }

    /**
     *
     * @param session
     * @param query
     * @param parametros
     * @return BigDecimal (Se pesquisa retornar null o valor de retorno será
     * BigDecimal.ZERO)
     */
    public BigDecimal getBigDecimal(EntityManager session, String query, Object... parametros) {
        //BigDecimal ret = getPojoUnique(session, BigDecimal.class, query, parametros);
        //return ret == null ? BigDecimal.ZERO : ret;

        Object o = getPojoUnique(session, BigDecimal.class, query, parametros);
        if (o == null) {
            return BigDecimal.ZERO;

        } else if (o instanceof BigDecimal) {
            BigDecimal ret = (BigDecimal) o;
            return ret;

        } else if (o instanceof Double) {
            Double ret = (Double) o;
            return BigDecimal.valueOf(ret);

        } else {
            return BigDecimal.ZERO;
        }

    }

    /**
     *
     * @param session
     * @param query
     * @param parametros
     * @return double (Se pesquisa retornar null o valor de retorno será 0.00)
     */
    public double getDouble(EntityManager session, String query, Object... parametros) {
        //Double ret = getPojoUnique(session, Double.class, query, parametros);
        //return ret == null ? 0.00 : ret;

        Object o = getPojoUnique(session, Double.class, query, parametros);
        if (o == null) {
            return 0.00;

        } else if (o instanceof BigDecimal) {
            BigDecimal ret = (BigDecimal) o;
            return ret.doubleValue();

        } else if (o instanceof Double) {
            Double ret = (Double) o;
            return ret;

        } else {
            return 0.00;
        }

    }

    /**
     *
     * @param session
     * @param query
     * @param parametros
     * @return Integer (Se pesquisa retornar null o valor de retorno será 0)
     */
    public Integer getInteger(EntityManager session, String query, Object... parametros) {

        Object o = getPojoUnique(session, Integer.class, query, parametros);
        if (o == null) {
            return 0;

        } else if (o instanceof Integer) {
            Integer ret = (Integer) o;
            return ret;

        } else if (o instanceof Long) {
            Integer ret = ((Long) o).intValue();
            return ret;

        } else {
            return 0;
        }

    }

    /**
     *
     * @param session
     * @param query
     * @param parametros
     * @return java.sql.Date
     */
    public java.sql.Date getDataSQL(EntityManager session, String query, Object... parametros) {

        Object o = getPojoUnique(session, java.sql.Date.class, query, parametros);
        if (o == null) {
            return null;

        } else if (o instanceof java.sql.Date) {
            java.sql.Date ret = (java.sql.Date) o;
            return ret;

        } else if (o instanceof java.util.Date) {
            java.util.Date ret = (java.util.Date) o;
            return DataUtil.getDateSQL(ret);

        } else {
            return null;
        }

    }

    /**
     *
     * @param session
     * @param query
     * @param parametros
     * @return java.util.Date
     */
    public java.util.Date getDataUtil(EntityManager session, String query, Object... parametros) {

        Object o = getPojoUnique(session, java.util.Date.class, query, parametros);
        if (o == null) {
            return null;

        } else if (o instanceof java.sql.Date) {
            java.sql.Date ret = (java.sql.Date) o;
            return ret;

        } else if (o instanceof java.util.Date) {
            java.util.Date ret = (java.util.Date) o;
            return ret;

        } else {
            return null;
        }

    }

    /**
     *
     * @param session
     * @param query
     * @param parametros
     * @return Long (Se pesquisa retornar null o valor de retorno será 0)
     */
    public Long getLong(EntityManager session, String query, Object... parametros) {

        Object o = getPojoUnique(session, Long.class, query, parametros);
        if (o == null) {
            return (long) 0;

        } else if (o instanceof Integer) {
            Integer ret = (Integer) o;
            return Long.valueOf(ret);

        } else if (o instanceof Long) {
            Long ret = (Long) o;
            return ret;

        } else if (o instanceof BigInteger) {
            Long ret = ((BigInteger) o).longValue();
            return ret;

        } else {
            return (long) 0;
        }

    }

    /**
     *
     * @param session
     * @param query
     * @param parametros
     * @return boolean (true se tiver dados false caso contrario)
     */
    @SuppressWarnings("rawtypes")
    public boolean verificarSeADados(EntityManager session, String query, Object... parametros) {

        Query qr = session.createQuery(query);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        List results = qr.getResultList();
        return results != null && !results.isEmpty();

    }

    /**
     *
     * @param <T>
     * @param session
     * @param classToSearch
     * @param query
     * @param maxResults
     * @param parametros
     * @return Retorna uma lista.
     *
     * @since Ley 02/05/2016
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> List<T> getList(EntityManager session, Class<T> classToSearch, String query, int maxResults, Object... parametros) {

        Query qr = session.createQuery(query);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        qr.setMaxResults(maxResults);

        List<T> toList = qr.getResultList();

        return toList;

    }

    /**
     * @param <T>
     * @param session
     * @param classToSearchPojo
     * @param key
     * @return Retorna um unico objeto.
     *
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T getPojo(EntityManager session, Class<T> classToSearchPojo, Serializable key) {
        Serializable pojo = session.find(classToSearchPojo, key);
        return (T) pojo;
    }

    /**
     * @param <T>
     * @param session
     * @param registroInicial
     * @param qtdeRegistro
     * @param classToSearch
     * @param query
     * @param parametros
     * @return Retorna uma lista de objetos.
     *
     */
    public <T extends Serializable> List<T> getPureList(EntityManager session, int registroInicial, int qtdeRegistro, Class<T> classToSearch, String query, Object... parametros) {

        Query qr = session.createQuery(query).setMaxResults(qtdeRegistro).setFirstResult(registroInicial);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        @SuppressWarnings("unchecked")
        List<T> toList = qr.getResultList();
        return toList;

    }

    /**
     * @param <T>
     * @param session
     * @param classToSearch
     * @param query
     * @param parametros
     * @return Retorna uma lista de objetos.
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> List<T> getPureList(EntityManager session, Class<T> classToSearch, String query, Object... parametros) {

        Query qr = session.createQuery(query);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        Object t = qr.getResultList();
        List<T> toList = (List<T>) t;

        return toList;

    }

    /**
     * @param session
     * @param SQL
     * @param parametros
     * @return boolean.
     */
    public boolean executeQuery(EntityManager session, String SQL, Object... parametros) {

        Query qr = session.createQuery(SQL);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        return qr.executeUpdate() > 0;

    }

    // --------- PESQUISAS COM NATIVE QUERY -------------------------------------------------------------
    
    /**
     * @param session
     * @param sql
     * @param parametros
     * @return Retorna um objeto com os campos da query.
     */
    public Object objectNativeQuery(EntityManager session, String sql, Object... parametros) {
         Query qr = session.createNativeQuery(sql);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }
        
        return qr.getSingleResult();
    }
    
    /**
     * @param session
     * @param sql
     * @param parametros
     * @return Retorna uma lista de objetos.
     */
    public List<?> getListNativeQuery(EntityManager session, String sql, Object... parametros) {

        Query qr = session.createNativeQuery(sql);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        return qr.getResultList();

    }

    /**
     *
     * @param session
     * @param regInicial
     * @param qtdeReg
     * @param sql
     * @param parametros
     * @return Retorna uma lista de objetos.
     */
    public List<?> getListNativeQuery(EntityManager session, int regInicial, int qtdeReg, String sql, Object... parametros) {

        Query qr = session.createNativeQuery(sql).setMaxResults(qtdeReg).setFirstResult(regInicial);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        return qr.getResultList();

    }

    /**
     *
     * @param session
     * @param maxResults
     * @param sql
     * @param parametros
     * @return Retorna uma lista de objetos.
     */
    public List<?> getListNativeQuery(EntityManager session, int maxResults, String sql, Object... parametros) {

        Query qr = session.createNativeQuery(sql).setMaxResults(maxResults);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        return qr.getResultList();

    }

    /**
     * @param session
     * @param SQL
     * @param parametros
     * @return boolean.
     */
    public boolean executeBNativeQuery(EntityManager session, String SQL, Object... parametros) {

        Query qr = session.createNativeQuery(SQL);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        return qr.executeUpdate() > 0;

    }

    /**
     *
     * @param session
     * @param query
     * @param parametros
     * @return (Se pesquisa retornar null o valor de retorno será 0)
     */
    public int getIntegerNativeQuery(EntityManager session, String query, Object... parametros) {

        Query qr = session.createNativeQuery(query);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        Object o = null;

        List<?> r = qr.getResultList();

        if (r != null && !r.isEmpty()) {
            o = r.get(0);
        }

        if (o == null) {
            return 0;

        } else if (o instanceof Integer) {
            Integer ret = (Integer) o;
            return ret;

        } else if (o instanceof Long) {
            Integer ret = ((Long) o).intValue();
            return ret;

        } else if (o instanceof BigInteger) {
            Integer ret = ((BigInteger) o).intValue();
            return ret;

        } else {
            return 0;
        }

    }

    /**
     *
     * @param session
     * @param query
     * @param parametros
     * @return (Se pesquisa retornar null o valor de retorno será 0)
     */
    public long getLongNativeQuery(EntityManager session, String query, Object... parametros) {

        Query qr = session.createNativeQuery(query);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        Object o = null;

        List<?> r = qr.getResultList();

        if (r != null && !r.isEmpty()) {
            o = r.get(0);
        }

        if (o == null) {
            return 0;

        } else if (o instanceof Integer) {
            Integer ret = (Integer) o;
            return ret;

        } else if (o instanceof Long) {
            Long ret = (Long) o;
            return ret;

        } else if (o instanceof BigInteger) {
            Long ret = ((BigInteger) o).longValue();
            return ret;

        } else {
            return 0;
        }

    }

    /**
     *
     * @param session
     * @param query
     * @param parametros
     * @return (Se pesquisa retornar null o valor de retorno será 0)
     */
    public BigDecimal getBigDecimalNativeQuery(EntityManager session, String query, Object... parametros) {

        Query qr = session.createNativeQuery(query);

        for (int i = 0; i < parametros.length; i++) {
            qr.setParameter(i + 1, parametros[i]);
        }

        Object o = null;

        List<?> r = qr.getResultList();

        if (r != null && !r.isEmpty()) {
            o = r.get(0);
        }

        if (o == null) {
            return BigDecimal.ZERO;

        } else if (o instanceof BigDecimal) {
            BigDecimal ret = (BigDecimal) o;
            return ret;

        } else if (o instanceof Number) {
            BigDecimal ret = (BigDecimal) o;
            return ret;

        } else if (o instanceof Double) {
            double ret = ((Double) o);
            return BigDecimal.valueOf(ret);

        } else {
            return BigDecimal.ZERO;
        }

    }

    public void gravaLog(EntityManager session, Exception erro, String operacao) {
        EntityManager em = session;
        try {
            Log log = getLog(null, operacao, erro);
            em.persist(log);
        } catch (Exception e) {
            rollback(e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void gravaLog(EntityManager session, String operacao) {
        gravaLog(session, null, operacao);
    }

    private Log getLog(Serializable pojo, String operacao, Exception erro) {
        Log log = getNewLog(erro);

        excecaoTelaNula();

        log.setAcao(operacao);
        log.setTela(getTela());

        if (pojo != null) {
            setLog(log, pojo);
        }
        return log;
    }

    private Log getNewLog(Exception erro) {
        Log log;

        if (erro != null) {
            log = new Log();
        } else {
            log = new Log();
        }

        return log;
    }

}