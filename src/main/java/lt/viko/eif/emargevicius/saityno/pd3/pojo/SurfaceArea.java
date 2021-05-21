package lt.viko.eif.emargevicius.saityno.pd3.pojo;

import javax.xml.bind.annotation.XmlType;

/**
 * Represents Surface Area object used by
 * {@link Planet}. It has 2
 * attributes: km2 (surface area in km^2), earths (surface are in proportion to
 * Earth)
 *
 * @author Emil
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 * @see Planet
 */
@XmlType(propOrder = {
    "km2",
    "earths"
})
public class SurfaceArea {

    private long km2;
    private float earths;

    /**
     * Getter for the surface area size measured in km^2
     *
     * @return surface area km^2
     */
    public long getKm2() {
        return km2;
    }

    /**
     * Setter for the surface area size measured in km^2
     *
     * @param km2 surface area km2
     */
    public void setKm2(long km2) {
        this.km2 = km2;
    }

    /**
     * Getter for the surface area size in proportion to earth
     *
     * @return surface area in proportion to earth.
     */
    public float getEarths() {
        return earths;
    }

    /**
     * Setter for the surface area size in proportion to earth.
     *
     * @param earths surface area in proportion to earth.
     */
    public void setEarths(float earths) {
        this.earths = earths;
    }

    /**
     * Override for toString() method from {@link java.lang.Object}
     *
     * @return a string representation of the object.
     * @see java.lang.Object
     */
    @Override
    public String toString() {
        return String.format("\t\tSurface Area:\n"
                + "\t\t\tKm^2:\t%s\n"
                + "\t\t\tEarths:\t%s\n", km2, earths);
    }
}
