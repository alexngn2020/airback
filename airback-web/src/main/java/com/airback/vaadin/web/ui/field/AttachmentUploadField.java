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
package com.airback.vaadin.web.ui.field;

import com.airback.core.utils.StringUtils;
import com.airback.module.ecm.domain.Content;
import com.airback.module.ecm.service.ResourceService;
import com.airback.spring.AppContextUtil;
import com.airback.vaadin.ui.IgnoreBindingField;
import com.airback.vaadin.web.ui.AttachmentDisplayComponent;
import com.airback.vaadin.web.ui.AttachmentPanel;
import com.vaadin.ui.Component;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author airback Ltd.
 * @since 4.5.3
 */
public class AttachmentUploadField extends IgnoreBindingField {
    private static final long serialVersionUID = 1L;

    private ResourceService resourceService;
    private AttachmentPanel attachmentPanel;
    private String attachmentPath;

    public AttachmentUploadField() {
        this(null);
    }

    public AttachmentUploadField(String attachmentPath) {
        this.attachmentPath = attachmentPath;
        resourceService = AppContextUtil.getSpringBean(ResourceService.class);
    }

    public void saveContentsToRepo(String attachmentPath) {
        attachmentPanel.saveContentsToRepo(attachmentPath);
    }

    @Override
    protected Component initContent() {
        attachmentPanel = new AttachmentPanel();
        if (StringUtils.isNotBlank(attachmentPath)) {
            List<Content> attachments = resourceService.getContents(attachmentPath);
            if (CollectionUtils.isNotEmpty(attachments)) {
                AttachmentDisplayComponent attachmentDisplayComponent = new AttachmentDisplayComponent(attachments);
                return new MVerticalLayout(attachmentDisplayComponent, attachmentPanel);
            } else {
                return attachmentPanel;
            }
        } else {
            return attachmentPanel;
        }
    }

    @Override
    protected void doSetValue(Object o) {

    }

    @Override
    public Object getValue() {
        return null;
    }
}
