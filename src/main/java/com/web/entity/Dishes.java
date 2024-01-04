package com.web.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Embeddable
public class Dishes {

	@Id
	@Column
	private Integer dishId;

	@Column
	private String dishName;

	@Column
	private Double dishCost;

	@Transient
	private Integer dishQuantity;

	public Dishes() {
		super();
	}

	public Dishes(Integer dishId, String dishName, Double dishCost, Integer dishQuantity) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.dishCost = dishCost;
		this.dishQuantity = dishQuantity;
	}

	public Integer getDishId() {
		return dishId;
	}

	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Double getDishCost() {
		return dishCost;
	}

	public void setDishCost(Double dishCost) {
		this.dishCost = dishCost;
	}

	public Integer getDishQuantity() {
		return dishQuantity;
	}

	public void setDishQuantity(Integer dishQuantity) {
		this.dishQuantity = dishQuantity;
	}

	@Override
	public String toString() {
		return "Dishes [dishId=" + dishId + ", dishName=" + dishName + ", dishCost=" + dishCost + ", dishQuantity="
				+ dishQuantity + "]";
	}

}
