package com.handson.user.util;

/**
 * This is common interface to calculate the distance
 * coordinates
 * 
 * @author PattathilS
 *
 */
public interface DistanceCalculator<T> {

	double findDistance(T start, T end);
}
