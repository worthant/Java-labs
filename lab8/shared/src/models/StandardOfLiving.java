package models;

import java.io.Serializable;

/**
 * Enum for different StandardOfLiving types
 */
public enum StandardOfLiving implements Serializable {
    ULTRA_HIGH,
    VERY_HIGH,
    HIGH,
    MEDIUM,
    AVERAGE,
    LOW,
    VERY_LOW,
    ULTRA_LOW,
    NIGHTMARE,
    DISASTROUS,
    CATASTROPHIC
}