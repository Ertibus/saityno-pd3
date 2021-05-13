package lt.viko.eif.emargevicius.saityno.pojo;

import javax.xml.bind.annotation.XmlType;

/**
 * Represents Temperature object used by
 * {@link Planet}. It has multiple
 * attributes: min, mean, max.
 *
 * @author Emil
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 * @see Planet
 */
@XmlType(propOrder = {
    "min",
    "mean",
    "max"
})
public class Temperature {

    private String min;
    private String mean;
    private String max;

    /**
     * Getter for the min temperature in Kelvin (K).
     *
     * @return min temperature (K).
     */
    public String getMin() {
        return min;
    }

    /**
     * Setter for the min temperature in Kelvin (K).
     *
     * @param min min temperature (K).
     */
    public void setMin(String min) {
        this.min = min;
    }

    /**
     * Getter for the mean temperature in Kelvin (K).
     *
     * @return mean temperature (K).
     */
    public String getMean() {
        return mean;
    }

    /**
     * Setter for the mean temperature in Kelvin (K).
     *
     * @param mean mean temperature (K).
     */
    public void setMean(String mean) {
        this.mean = mean;
    }

    /**
     * Getter for the max temperature in Kelvin (K).
     *
     * @return max temperature (K).
     */
    public String getMax() {
        return max;
    }

    /**
     * Setter for the max temperature in Kelvin (K).
     *
     * @param max max temperature (K).
     */
    public void setMax(String max) {
        this.max = max;
    }

    /**
     * Override for toString() method from {@link java.lang.Object}
     *
     * @return a string representation of the object.
     * @see java.lang.Object
     */
    @Override
    public String toString() {
        return String.format("\t\tTemperature:\n"
                + "\t\t\tMin:\t%s\n"
                + "\t\t\tMean:\t%s\n"
                + "\t\t\tMax:\t%s\n", min, mean, max);
    }

}
