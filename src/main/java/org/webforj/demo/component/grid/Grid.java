package org.webforj.demo.component.grid;

import com.google.gson.annotations.SerializedName;
import com.webforj.PendingResult;
import com.webforj.annotation.Attribute;
import com.webforj.annotation.JavaScript;
import com.webforj.component.element.ElementComposite;
import com.webforj.component.element.PropertyDescriptor;
import com.webforj.component.element.annotation.NodeName;
import com.webforj.concern.*;
import com.webforj.dispatcher.EventListener;
import com.webforj.dispatcher.ListenerRegistration;
import org.webforj.demo.component.grid.event.CellClickedEvent;
import org.webforj.demo.component.grid.event.CellValueChangedEvent;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 *
 * @author ElyasSalar
 * @since 24.20
 */
@NodeName("dwc-grid")
@JavaScript(value = "http://localhost:8888/basis/grid@33/dwc-grid.js", top = true, attributes = {
		@Attribute(name = "type", value = "module")})
public class Grid extends ElementComposite
		implements
			HasClassName<Grid>,
			HasProperty<Grid>,
			HasAttribute<Grid>,
			HasStyle<Grid>,
			HasJsExecution {

	public enum Theme {
		@SerializedName("alpine")
		ALPINE,

		@SerializedName("balham")
		BALHAM,

		@SerializedName("quartz")
		QUARTZ,
	}

	/**
	 * Property for options configuration of the grid.
	 */
	private final PropertyDescriptor<Object> optionsProp = PropertyDescriptor.property("options",
			null);

	/**
	 * Property for setting a theme for the grid.
	 */
	private final PropertyDescriptor<Theme> themeProp = PropertyDescriptor.property("theme",
			Theme.ALPINE);


	public ListenerRegistration<CellValueChangedEvent> addCellValueChangedListener(
					EventListener<CellValueChangedEvent> listener) {
		return super.addEventListener(CellValueChangedEvent.class, listener);
	}

	public ListenerRegistration<CellClickedEvent> addCellClickedListener(
					EventListener<CellClickedEvent> listener) {
		return super.addEventListener(CellClickedEvent.class, listener);
	}

	public PendingResult<Object> showColumnFilter(String columnKey) {
		Objects.requireNonNull(columnKey, "Column key cannot be null");
		return getBoundComponent().callJsFunctionAsync("showColumnFilter", columnKey);
	}

	public PendingResult<Object> showColumnMenu(String columnKey) {
		Objects.requireNonNull(columnKey, "Column key cannot be null");
		return getBoundComponent().callJsFunctionAsync("showColumnMenu", columnKey);
	}

	/**
	 * Gets the current grid configuration options.
	 *
	 * @return the current grid options as an {@code Object}.
	 */
	public Object getOptions() {
		return get(this.optionsProp);
	}

	/**
	 * Sets the grid configuration options.
	 *
	 * @param options the grid options to configure the grid behavior and appearance.
	 * @return the current {@code Grid} instance for method chaining.
	 */
	public Grid setOptions(Object options) {
		set(this.optionsProp, options);
		return this;
	}

	/**
	 * Gets the current theme for the grid.
	 *
	 * @return the current {@code Theme} of the grid.
	 */
	public Theme getTheme() {
		return get(this.themeProp);
	}

	/**
	 * Sets the theme for the grid.
	 *
	 * @param theme the {@code Theme} to apply (e.g., ALPINE, BALHAM, MATERIAL).
	 * @return the current {@code Grid} instance for method chaining.
	 */
	public Grid setTheme(Theme theme) {
		set(this.themeProp, theme);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Grid addClassName(String... classNames) {
		this.getBoundComponent().addClassName(classNames);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Grid removeClassName(String... classNames) {
		this.getBoundComponent().removeClassName(classNames);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object executeJs(String js) {
		return this.getBoundComponent().executeJs(js);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PendingResult<Object> executeJsAsync(String js) {
		return this.getBoundComponent().executeJsAsync(js);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeJsVoidAsync(String js) {
		this.getBoundComponent().executeJsVoidAsync(js);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStyle(String property) {
		return this.getBoundComponent().getStyle(property);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getComputedStyle(String property) {
		return this.getBoundComponent().getComputedStyle(property);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Grid setStyle(String property, String value) {
		this.getBoundComponent().setStyle(property, value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Grid removeStyle(String property) {
		this.getBoundComponent().removeStyle(property);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getProperty(String property) {
		return this.getBoundComponent().getProperty(property);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Grid setProperty(String property, Object value) {
		this.getBoundComponent().setProperty(property, value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <V> V getProperty(String property, Type typeOfV) {
		return this.getBoundComponent().getProperty(property, typeOfV);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <V> V getProperty(String property, Class<V> classOfV) {
		return this.getBoundComponent().getProperty(property, classOfV);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAttribute(String attribute) {
		return this.getBoundComponent().getAttribute(attribute);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Grid setAttribute(String attribute, String value) {
		this.getBoundComponent().setAttribute(attribute, value);
		return this;
	}
}
