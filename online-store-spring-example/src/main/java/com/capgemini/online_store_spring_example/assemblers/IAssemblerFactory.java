package com.capgemini.online_store_spring_example.assemblers;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.online_store_spring_example.entities.IEntity;
import com.capgemini.online_store_spring_example.viewmodels.IViewModel;

public interface IAssemblerFactory <M extends IViewModel, E extends IEntity> { 

	public E toEntity(M viewModel);
	
	public default List<E> toEntity(List<M> viewModelList){
		
		List<E> entityList = new LinkedList<E>();
		
		for(M m : viewModelList) {
			E entity = this.toEntity(m);
			entityList.add(entity);
		}
		
		return entityList;
	}
	
	public M toViewModel(E entity);
	
	public default List<M> toViewModel(List<E> entityList){
		
		List<M> viewModelList = new LinkedList<M>();
		
		for(E e : entityList) {
			M viewModel = this.toViewModel(e);
			viewModelList.add(viewModel);
		}
		
		return viewModelList;
	}
	
}
