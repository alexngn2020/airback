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

import com.airback.vaadin.UserUIContext;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.ListSelect;

import java.util.Collection;

/**
 * @author airback Ltd.
 * @since 4.3.0
 */
public class I18nValueListSelect<T extends Enum<T>> extends ListSelect<T> {
    private static final long serialVersionUID = 1L;

    private Class<T> enumCls;

    public void loadData(Collection<T> values) {
        this.setRows(4);
        this.setItems(values);
        this.setItemCaptionGenerator((ItemCaptionGenerator<T>) item -> UserUIContext.getMessage(item));
    }
}
