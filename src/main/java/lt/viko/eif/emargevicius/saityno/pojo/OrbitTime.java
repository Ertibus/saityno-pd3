package lt.viko.eif.emargevicius.saityno.pojo;

import javax.xml.bind.annotation.XmlType;

/**
 * Represents Orbit Time object used by
 * {@link Planet}. It has 2 attributes: years, days
 *
 * @author Emil
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 * @see Planet
 */
@XmlType(propOrder = {
    "years",
    "days"
}, name = "orbit-time")
public class OrbitTime {
    private float years;
    private float days;
    /**
     * Getter for years it takes for the planet to rotate around the sun.
     * @return orbit time in years.
     */
    public float getYears() {
        return years;
    }
    /**
     * Setter for years it takes for the planet to rotate around the sun.
     * @param years orbit time in years.
     */
    public void setYears(float years) {
        this.years = years;
    }
    /**
     * Getter for days it takes for the planet to rotate around the sun.
     * @return orbit time in days.
     */
    public float getDays() {
        return days;
    }
    /**
     * Setter for days it takes for the planet to rotate around the sun
     * @param days orbit time in days.
     */
    public void setDays(float days) {
        this.days = days;
    }

    /**
     * Override for toString() method from {@link java.lang.Object}
     *
     * @return a string representation of the object.
     * @see java.lang.Object
     */
    @Override
    public String toString() {
        return String.format("\t\tOrbit time:\n"
                + "\t\t\tIn Years:\t%f\n"
                + "\t\t\tIn Days:\t%f\n", years, days);
    }
    
}
