package com.nagarro.training.assignment3.flight;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author hiteshgarg 
 * A class Flight to store all the data related to Flights
 *         that is received from the CSV files
 */
@Entity
@Table(name = "flight_data")
public class Flight implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int id;
	

	@Id
	@Column(name = "flight_no")
	private String flightNo;
	
	@Column(name = "valid_till")
	private Date validTill;

	@Column(name = "flight_time")
	private String flightTime;
	
	@Column(name = "flight_duration")
	private String flightDuration;
	
	@Column(name = "fare")
	private Integer fare;
	
	@Column(name = "seat_avail")
	private String seatAvail;
	
	@Id
	@Column(name = "flight_class")
	private String flightClass;
	
	@ManyToOne(targetEntity= Location.class)
	@JoinColumn(name="arr_loc", referencedColumnName="id")
	private Location arrLoc;
	
	@ManyToOne(targetEntity= Location.class)
	@JoinColumn(name="dep_loc", referencedColumnName="id")
	private Location depLoc;

	
	/**
	 * @return the arrLoc
	 */
	public Location getArrLoc() {
		return arrLoc;
	}

	/**
	 * @param arrLoc the arrLoc to set
	 */
	public void setArrLoc(Location arrLoc) {
		this.arrLoc = arrLoc;
	}

	/**
	 * @return the depLoc
	 */
	public Location getDepLoc() {
		return depLoc;
	}

	/**
	 * @param depLoc the depLoc to set
	 */
	public void setDepLoc(Location depLoc) {
		this.depLoc = depLoc;
	}
//	/**
//	 * @return the id
//	 */
//	public int getId() {
//		return id;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(int id) {
//		this.id = id;
//	}

	/**
	 * @return the flightNo
	 */
	public String getFlightNo() {
		return flightNo;
	}

	/**
	 * @param flightNo the flightNo to set
	 */
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	/**
	 * @return the validTill
	 */
	public Date getValidTill() {
		return validTill;
	}

	/**
	 * @param validTill the validTill to set
	 */
	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	/**
	 * @return the flightTime
	 */
	public String getFlightTime() {
		return flightTime;
	}

	/**
	 * @param flightTime the flightTime to set
	 */
	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	/**
	 * @return the flightDuration
	 */
	public String getFlightDuration() {
		return flightDuration;
	}

	/**
	 * @param flightDuration the flightDuration to set
	 */
	public void setFlightDuration(String flightDuration) {
		this.flightDuration = flightDuration;
	}

	/**
	 * @return the fare
	 */
	public Integer getFare() {
		return fare;
	}

	/**
	 * @param fare the fare to set
	 */
	public void setFare(Integer fare) {
		this.fare = fare;
	}

	/**
	 * @return the seatAvail
	 */
	public String getSeatAvail() {
		return seatAvail;
	}

	/**
	 * @param seatAvail the seatAvail to set
	 */
	public void setSeatAvail(String seatAvail) {
		this.seatAvail = seatAvail;
	}

	/**
	 * @return the flightClass
	 */
	public String getFlightClass() {
		return flightClass;
	}

	/**
	 * @param flightClass the flightClass to set
	 */
	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	
	public static Comparator<Flight> FareSorter = new Comparator<Flight>() {
		
		public int compare(Flight obj1, Flight obj2) {
			return obj1.getFare() - obj2.getFare();
		}
	};

	public static Comparator<Flight> FareDurationSorter = new Comparator<Flight>() {
	
		public int compare(Flight obj1, Flight obj2) {
			if (obj1.getFare().equals(obj2.getFare())) {
				return obj1.getFlightDuration().compareTo(
						obj2.getFlightDuration());
			} else {
				return obj1.getFare() - obj2.getFare();
			}
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((flightClass == null) ? 0 : flightClass.hashCode());
		result = prime * result
				+ ((flightNo == null) ? 0 : flightNo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (flightClass == null) {
			if (other.flightClass != null)
				return false;
		} else if (!flightClass.equals(other.flightClass))
			return false;
		if (flightNo == null) {
			if (other.flightNo != null)
				return false;
		} else if (!flightNo.equals(other.flightNo))
			return false;
		return true;
	}

}
