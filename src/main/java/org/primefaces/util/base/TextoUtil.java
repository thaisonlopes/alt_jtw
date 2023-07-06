package org.primefaces.util.base;


public class TextoUtil {

	/**
	 * Retorna para um determinado número se ele pode ser convertido em double
	 * Através de Double.parseDouble
	 */
	public static boolean isParseDouble(String texto) {
		if (texto == null) {
			return false;
		}

		else if (texto.length() == 0) {
			return false;
		}

		else if (texto.trim().equals("")) {
			return false;
		}

		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);

			if (!Character.isDigit(c)) {
				if (c != '.' && c != '-') {
					return false;
				}
				if (c == '-') {
					if (i != 0) {
						return false;
					}
				}
				if (c == '.') {
					if (i < texto.length() - 6) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Retorna para um determinado número se ele está no formato decimal ou
	 * inteiro
	 */
	public static boolean isNumerico(String texto) {
		if (texto == null) {
			return false;
		}

		else if (texto.length() == 0) {
			return false;
		}

		else if (texto.trim().equals("")) {
			return false;
		}

		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);

			if (!Character.isDigit(c)) {
				if (c != ',' && c != '.' && c != '-') {
					return false;
				}
				if (c == '-') {
					if (i != 0) {
						return false;
					}
				}
				if (c == ',') {
					if (i != texto.length() - 3) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/** Retorna para um determinado número se ele está no formato inteiro */
	public static boolean isInteiro(String texto) {
		if (texto == null) {
			return false;
		}
		if (texto.trim().equals("")) {
			return false;
		}
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);

			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public static String tiraCaracteresEspeciais(String s) {
		String retorno = "";

		if (s == null) {
			return "";
		}

		for (int i = 0; i < s.length(); i++) {
			if (s.substring(i, i + 1).equals("ç")) {
				retorno += "c";
			} else if (s.substring(i, i + 1).equals("ã") || s.substring(i, i + 1).equals("á")
					|| s.substring(i, i + 1).equals("â") || s.substring(i, i + 1).equals("à")) {
				retorno += "a";
			} else if (s.substring(i, i + 1).equals("é") || s.substring(i, i + 1).equals("ê")) {
				retorno += "e";
			} else if (s.substring(i, i + 1).equals("í")) {
				retorno += "i";
			} else if (s.substring(i, i + 1).equals("õ") || s.substring(i, i + 1).equals("ó")
					|| s.substring(i, i + 1).equals("ô")) {
				retorno += "o";
			} else if (s.substring(i, i + 1).equals("ú")) {
				retorno += "u";
			} else if (s.substring(i, i + 1).equals("Ç")) {
				retorno += "C";
			} else if (s.substring(i, i + 1).equals("Ã") || s.substring(i, i + 1).equals("Á")
					|| s.substring(i, i + 1).equals("Â") || s.substring(i, i + 1).equals("À")) {
				retorno += "A";
			} else if (s.substring(i, i + 1).equals("É") || s.substring(i, i + 1).equals("Ê")) {
				retorno += "E";
			} else if (s.substring(i, i + 1).equals("Í")) {
				retorno += "I";
			} else if (s.substring(i, i + 1).equals("Õ") || s.substring(i, i + 1).equals("Ó")
					|| s.substring(i, i + 1).equals("Ô")) {
				retorno += "O";
			} else if (s.substring(i, i + 1).equals("Ú")) {
				retorno += "U";
			} else if (s.substring(i, i + 1).equals("º") || s.substring(i, i + 1).equals("ª")) {
				retorno += "";
			} else {
				retorno += s.substring(i, i + 1);
			}
		}
		return retorno;
	}

	public static String completaADireita(String completado, int tamanho, String preenchedor) {
		try {

			if (preenchedor.equals("")) {
				throw new AltException("Preenchedor Vazio");
			}

			if (completado == null) {
				return completaAEsquerda(" ", tamanho, preenchedor);
			}

			while (completado.length() < tamanho) {
				completado += preenchedor;
			}

			return completado.substring(0, tamanho);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String completaAEsquerda(String completado, int tamanho, String preenchedor) {
		String temp = "";
		try {
			if (preenchedor.equals("")) {
				throw new AltException("Preenchedor Vazio");
			}

			if (completado == null) {
				completado = "";
			}
			int itemp = tamanho - completado.trim().length();

			while (temp.length() < itemp) {
				temp += preenchedor;
			}

			temp += completado.trim();

			return temp.substring(0, tamanho);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String soDigitos(String texto) {
		String ret = "";

		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			if (Character.isDigit(c)) {
				ret += c;
			}
		}
		return ret;
	}

	public static String getCNPJComCaracteres(String cnpjSoComDigitos) {
		String ret = "";

		String c = cnpjSoComDigitos;

		if (c == null) {
			return "";
		}
		if (c.length() == 18) {
			return c;
		}
		if (c.length() != 14) {
			return "";
		}

		ret = c.substring(0, 2) + "." + c.substring(2, 5) + "." + c.substring(5, 8) + "/" + c.substring(8, 12) + "-"
				+ c.substring(12);

		return ret;
	}

	public static String getCPFComCaracteres(String cpfSoComDigitos) {
		String ret = "";

		String c = cpfSoComDigitos;

		if (c == null) {
			return "";
		}
		if (c.length() == 14) {
			return c;
		}
		if (c.length() != 11) {
			return "";
		}

		ret = c.substring(0, 3) + "." + c.substring(3, 6) + "." + c.substring(6, 9) + "-" + c.substring(9, 11);

		return ret;
	}

	public static String tiraEspacos(String s) {
		String ret = s.replace(" ", "");
		ret = ret.replace("\n", "");
		ret = ret.replace("\t", "");
		ret = ret.replace("\r", "");

		return ret;
	}

	public static void replaceAllBuilder(StringBuilder buffer, String substituido, String substituto) {
		if (buffer == null) {
			return;
		}
		if (substituido == null) {
			return;
		}
		if (substituto == null) {
			return;
		}
		int index = buffer.indexOf(substituido);

		while ((index >= 0) && (index < buffer.length())) {
			buffer.replace(index, index + substituido.length(), substituto);

			index = buffer.indexOf(substituido);
		}
	}

	public static void replaceAllBuffer(StringBuffer buffer, String substituido, String substituto) {
		if (buffer == null) {
			return;
		}
		if (substituido == null) {
			return;
		}
		if (substituto == null) {
			return;
		}
		int index = buffer.indexOf(substituido);

		while ((index >= 0) && (index < buffer.length())) {
			buffer.replace(index, index + substituido.length(), substituto);

			index = buffer.indexOf(substituido);
		}
	}

	/** Retorna "" se o campo for nulo */
	public static String brancoSeNulo(String texto) {
		if (texto == null) {
			return "";
		}
		return texto;
	}

	public static String getVReal(String s) {

		String ret = "0,00";

		if (s != null && !s.equals("")) {
			ret = s.replace(".", "").replace("-", "");

		}

		return ret;
	}

	public static String getVRealComSinal(String s) {

		String ret = "0,00";

		if (s != null && !s.equals("")) {
			ret = s.replace(".", "");

		}

		return ret;
	}

}

