package usuarioMiembroYFecha;

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

}
