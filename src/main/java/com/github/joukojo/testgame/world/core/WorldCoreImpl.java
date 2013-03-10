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

	private transient final Map<String, List<Drawable>> drawableObjects;
	private transient final Map<String, List<Moveable>> moveableObjects;

	public WorldCoreImpl() {
		drawableObjects = new Hashtable<String, List<Drawable>>();
		moveableObjects = new Hashtable<String, List<Moveable>>();
	}

	public Drawable getDrawableObject(final String objectName) {
		Drawable drawable = null;

		final List<Drawable> drawables = getDrawableObjects(objectName);
		if (drawables == null || drawables.isEmpty()) {
			LOG.debug("can't find drawable object '{}'", objectName);
		} else {
			drawable = drawables.get(0);
		}
		return drawable;
	}

	public List<Drawable> getDrawableObjects(final String objectName) {
		return drawableObjects.get(objectName);
	}

	public void addObject(final String objectName, final Drawable drawable) {
		if (getDrawableObjects().containsKey(objectName)) {
			getDrawableObjects().get(objectName).add(drawable);
		} else {
			final List<Drawable> objectList = new ArrayList<Drawable>();
			objectList.add(drawable);
			getDrawableObjects().put(objectName, objectList);
		}
	}

	@Override
	public List<Moveable> getAllMoveables() {
		final Collection<List<Moveable>> allValues = getMoveableObjects()
				.values();
		final List<Moveable> allObjects = new ArrayList<Moveable>();
		for (final List<Moveable> list : allValues) {
			allObjects.addAll(list);
		}

		return allObjects;
	}

	@Override
	public void cleanMoveables() {
		final Set<String> keys = getMoveableObjects().keySet();

		for (final String key : keys) {
			final List<Moveable> entries = getMoveableObjects().get(key);
			final List<Moveable> activeEntries = cleanMoveables(entries);

			getMoveableObjects().put(key, activeEntries);

		}

	}

	private List<Moveable> cleanMoveables(final List<Moveable> entries) {
		final List<Moveable> activeEntries = new ArrayList<Moveable>();

		for (final Moveable moveable : entries) {

			if (!moveable.isDestroyed()) {
				activeEntries.add(moveable);
			}

		}
		return activeEntries;
	}

	@Override
	public List<Drawable> getAllDrawables() {
		final Collection<List<Drawable>> allValues = getDrawableObjects()
				.values();

		final List<Drawable> allObjects = new ArrayList<Drawable>();
		for (final List<Drawable> list : allValues) {
			allObjects.addAll(list);
		}
		return allObjects;
	}

	@Override
	public void addMoveable(final String objectName, final Moveable moveable) {

		final boolean containsKey = getMoveableObjects()
				.containsKey(objectName);

		if (containsKey) {
			final List<Moveable> list = getMoveableObjects().get(objectName);
			list.add(moveable);
		} else {
			final List<Moveable> list = new ArrayList<Moveable>();
			list.add(moveable);
			getMoveableObjects().put(objectName, list);
		}

	}

	@Override
	public Moveable getMoveable(final String objectName) {
		Moveable moveable = null;
		
		final List<Moveable> objects = getMoveableObjects(objectName);
		
		if (objects != null && !objects.isEmpty()) {
			moveable = objects.get(0);
		}
		return moveable;
	}

	@Override
	public List<Moveable> getMoveableObjects(final String objectName) {
		final List<Moveable> allObjects = new ArrayList<Moveable>();

		final List<Moveable> moveableObjs = moveableObjects
				.get(objectName);

		if (moveableObjs != null) {
			allObjects.addAll(moveableObjs);
		}

		return allObjects;

	}

	@Override
	public void removeMoveable(final String objectName, final Moveable moveable) {
		final List<Moveable> objects = getMoveableObjects(objectName);

		final boolean isRemoved = objects.remove(moveable);

		LOG.debug("isRemoved: {}", isRemoved);

	}

	@Override
	public List<String> getMoveableObjectNames() {

		return new ArrayList<String>(getMoveableObjects().keySet());
	}

	@Override
	public void resetWorld() {
		LOG.debug("resetting world");
		getMoveableObjects().clear();
		getDrawableObjects().clear();

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Map<String, List<Drawable>> getDrawableObjects() {
		return drawableObjects;
	}

	public Map<String, List<Moveable>> getMoveableObjects() {
		return moveableObjects;
	}

}
