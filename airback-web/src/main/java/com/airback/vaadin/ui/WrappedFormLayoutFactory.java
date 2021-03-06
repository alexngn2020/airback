/**
 * Copyright © airback
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.airback.vaadin.ui;

import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;

import java.util.Set;

/**
 * @author airback Ltd
 * @since 5.2.1
 */
public abstract class WrappedFormLayoutFactory implements IFormLayoutFactory {
    protected IFormLayoutFactory wrappedLayoutFactory;

    IFormLayoutFactory getWrappedFactory() {
        return wrappedLayoutFactory;
    }

    @Override
    public final HasValue<?> attachField(Object propertyId, HasValue<?> field) {
        return wrappedLayoutFactory.attachField(propertyId, field);
    }

    @Override
    public final Set<String> bindFields() {
        return wrappedLayoutFactory.bindFields();
    }
}
