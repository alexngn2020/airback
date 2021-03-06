/**
 * Copyright © airback
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.airback.module.project.view.settings;

import com.airback.form.view.LayoutType;
import com.airback.module.project.domain.ProjectMember;
import com.airback.module.project.i18n.ProjectI18nEnum;
import com.airback.module.project.i18n.ProjectMemberI18nEnum;
import com.airback.vaadin.UserUIContext;
import com.airback.vaadin.ui.AbstractFormLayoutFactory;
import com.airback.vaadin.ui.FormContainer;
import com.airback.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.data.HasValue;

/**
 * @author airback Ltd.
 * @since 1.0
 */
public class ProjectMemberFormLayoutFactory extends AbstractFormLayoutFactory {
    private GridFormLayoutHelper informationLayout;

    @Override
    public AbstractComponent getLayout() {
        final FormContainer layout = new FormContainer();
        informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(LayoutType.TWO_COLUMN);
        layout.addSection(UserUIContext.getMessage(ProjectMemberI18nEnum.FORM_INFORMATION_SECTION), informationLayout.getLayout());
        return layout;
    }

    @Override
    protected HasValue<?> onAttachField(Object propertyId, final HasValue<?> field) {
        if (propertyId.equals("memberFullName")) {
            return informationLayout.addComponent(field, UserUIContext.getMessage(ProjectMemberI18nEnum.FORM_USER), 0, 0, 2);
        } else if (propertyId.equals("projectroleid")) {
            return informationLayout.addComponent(field, UserUIContext.getMessage(ProjectMemberI18nEnum.FORM_ROLE), 0, 1, 2);
        } else if (ProjectMember.Field.billingrate.equalTo(propertyId)) {
            return informationLayout.addComponent(field, UserUIContext.getMessage(ProjectI18nEnum.FORM_BILLING_RATE), 0, 2);
        } else if (ProjectMember.Field.overtimebillingrate.equalTo(propertyId)) {
            return informationLayout.addComponent(field, UserUIContext.getMessage(ProjectI18nEnum.FORM_OVERTIME_BILLING_RATE), 1, 2);
        }
        return null;
    }
}
