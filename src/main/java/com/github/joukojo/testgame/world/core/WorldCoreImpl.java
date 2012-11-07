package com.github.joukojo.testgame.world.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldCoreImpl implements WorldCore {

	private final static Logger LOG = LoggerFactory
			.getLogger(WorldCoreImpl.class);

	private final Map<String, List<Drawable>> drawableObjects;
	private final Map<String, List<Moveable>> moveableObjects;

	public WorldCoreImpl() {
		drawableObjects = new Hashtable<String, List<Drawable>>();
		moveableObjects = new Hashtable<String, List<Moveable>>();
	}

	public Drawable getDrawableObject(final String objectName) {
		final List<Drawable> drawables = getDrawableObjects(objectName);

		if (drawables != null && !drawables.isEmpty()) {
			return drawables.get(0);
		} else {
			LOG.debug("can't find drawable object '{}'", objectName);
			return null;
		}
	}

	public List<Drawable> getDrawableObjects(final String objectName) {
		return drawableObjects.get(objectName);
	}

	public void addObject(final String objectName, final Drawable drawable) {
		if (drawableObjects.containsKey(objectName)) {
			drawableObjects.get(objectName).add(drawable);
		} else {
			final List<Drawable> objectList = new ArrayList<Drawable>();
			objectList.add(drawable);
			drawableObjects.put(objectName, objectList);
		}
	}

	@Override
	public List<Moveable> getAllMoveables() {
		final Collection<List<Moveable>> allValues = moveableObjects.values();
		final List<Moveable> allMoveableObjects = new ArrayList<Moveable>();
		for (final List<Moveable> list : allValues) {
			allMoveableObjects.addAll(list);
		}

		return allMoveableObjects;
	}

	@Override
	public void cleanMoveables() {
		final Set<String> keys = moveableObjects.keySet();

		for (final String key : keys) {
			final List<Moveable> entries = moveableObjects.get(key);
			final List<Moveable> stillActiveEntries = new ArrayList<Moveable>();

			for (final Moveable moveable : entries) {
				
				if (!moveable.isDestroyed()) {
					stillActiveEntries.add(moveable);
				}
				
			}

			moveableObjects.put(key, stillActiveEntries);

		}

	}

	@Override
	public List<Drawable> getAllDrawables() {
		final Collection<List<Drawable>> allValues = drawableObjects.values();

		final List<Drawable> allDrawableObjects = new ArrayList<Drawable>();
		for (final List<Drawable> list : allValues) {
			allDrawableObjects.addAll(list);
		}
		return allDrawableObjects;
	}

	@Override
	public void addMoveable(final String objectName, final Moveable moveable) {

		final boolean containsKey = moveableObjects.containsKey(objectName);

		if (containsKey) {
			final List<Moveable> list = moveableObjects.get(objectName);
			list.add(moveable);
		} else {
			final List<Moveable> list = new ArrayList<Moveable>();
			list.add(moveable);
			moveableObjects.put(objectName, list);
		}

	}

	@Override
	public Moveable getMoveable(final String objectName) {
		final List<Moveable> objects = getMoveableObjects(objectName);
		Moveable moveable = null;
		if (objects != null && !objects.isEmpty()) {
			moveable = objects.get(0);
		}
		return moveable;
	}

	@Override
	public List<Moveable> getMoveableObjects(final String objectName) {
		final List<Moveable> allMoveableObjects = new ArrayList<Moveable>();
		
		final List<Moveable> moveableObjectList = moveableObjects.get(objectName);
		
		if( moveableObjectList != null ) {
			allMoveableObjects.addAll(moveableObjectList);
		}
		
		return allMoveableObjects;

	}

	@Override
	public void removeMoveable(final String objectName, final Moveable moveable) {
		final List<Moveable> objects = getMoveableObjects(objectName);

		final boolean isRemoved = objects.remove(moveable);

		LOG.debug("isRemoved: {}", isRemoved);
		
	}

	@Override
	public List<String> getMoveableObjectNames() {
		
		return new ArrayList<String>(moveableObjects.keySet());
	}
	
	@Override
	public void resetWorld() {
		LOG.debug("resetting world"); 
		moveableObjects.clear();
		drawableObjects.clear();
		
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
