package usuarioMiembroYFecha;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {

	Date fecha;
	/**
	 * Constructor de Fecha sin parametro.
	 */
	public Fecha() {

		this.fecha = new Date();

	}
	/**
	 * Constructor de Fecha con parametro, representa una fecha.
	 * @param yyyyMMdd
	 */
	public Fecha(String yyyyMMdd) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		String strFecha = yyyyMMdd;
		this.fecha = null;
		try {

			this.fecha = formatoDelTexto.parse(strFecha);

		} catch (ParseException ex) {

			ex.printStackTrace();

		}
	}
	/**
	 * Verifica si la fecha es anterior que la fecha pasada por parametro.
	 * @param cuando tipo Fecha
	 * @return tipo boolean
	 */
	protected boolean antesQue(Fecha cuando) {
		return this.getFecha().before(cuando.getFecha());

	}
	/**
	 Verifica si la fecha es posterior que la fecha pasada por parametro.
	 * @param cuando tipo Fecha
	 * @return tipo boolean
	 */
	protected boolean DespuesQue(Fecha cuando) {
		return this.getFecha().after(cuando.getFecha());

	}
	/**
	 * 
	 * Devuelve cuantos dias faltan para una fecha aDate.
	 * Si la fecha es anterior, o si es la misma fecha,
	 * deuelve 0.
	 * 
	 */
	public Integer diasQFaltan(Fecha aDate) {
		return null;
	}
	public Fecha fechaDeHoy() {

		return new Fecha();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	public String toString() {
	
		return this.getFecha().toLocaleString().substring(0, 10);
	}
	public static int fechasDiferenciaEnDias(Date fechaInicial, Date fechaFinal) {

		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String fechaInicioString = df.format(fechaInicial);
		try {
		fechaInicial = df.parse(fechaInicioString);
		}
		catch (ParseException ex) {
		}

		String fechaFinalString = df.format(fechaFinal);
		try {
		fechaFinal = df.parse(fechaFinalString);
		}
		catch (ParseException ex) {
		}

		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ( (int) dias);
		}
	
	public static void main(String[] args) {
		Fecha fe = new Fecha("02-07-2010");
		Fecha fa = new Fecha("05-07-2010");
		Fecha ft = new Fecha("01-03-2010");
		System.out.println(ft.fechasDiferenciaEnDias(fa.getFecha(),fe.getFecha()));
	}
}
