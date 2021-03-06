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
package com.airback.module.project.view.page;

import com.airback.core.SecureAccessException;
import com.airback.module.page.domain.Page;
import com.airback.module.project.CurrentProjectVariables;
import com.airback.module.project.ProjectRolePermissionCollections;
import com.airback.module.project.event.PageEvent;
import com.airback.module.project.service.ProjectPageService;
import com.airback.module.project.view.ProjectBreadcrumb;
import com.airback.module.project.view.ProjectGenericPresenter;
import com.airback.module.project.view.ProjectView;
import com.airback.spring.AppContextUtil;
import com.airback.vaadin.AppUI;
import com.airback.vaadin.EventBusFactory;
import com.airback.vaadin.UserUIContext;
import com.airback.vaadin.event.IEditFormHandler;
import com.airback.vaadin.mvp.LoadPolicy;
import com.airback.vaadin.mvp.ScreenData;
import com.airback.vaadin.mvp.ViewManager;
import com.airback.vaadin.mvp.ViewScope;
import com.vaadin.ui.HasComponents;

/**
 * @author airback Ltd.
 * @since 4.4.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class PageAddPresenter extends ProjectGenericPresenter<PageAddView> {
    private static final long serialVersionUID = 1L;

    public PageAddPresenter() {
        super(PageAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new IEditFormHandler<Page>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(Page page) {
                savePage(page);
                EventBusFactory.getInstance().post(new PageEvent.GotoRead(this, page));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new PageEvent.GotoList(this, null));
            }

            @Override
            public void onSaveAndNew(Page page) {
                savePage(page);
                EventBusFactory.getInstance().post(new PageEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.PAGES)) {
            ProjectView projectView = (ProjectView) container;
            projectView.gotoSubView(ProjectView.PAGE_ENTRY, view);

            Page page = (Page) data.getParams();
            view.editItem(page);

            ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            if (page.getPath().equals("")) {
                breadcrumb.gotoPageAdd();
            } else {
                breadcrumb.gotoPageEdit(page);
            }
        } else {
            throw new SecureAccessException();
        }
    }

    private void savePage(Page page) {
        ProjectPageService pageService = AppContextUtil.getSpringBean(ProjectPageService.class);

        pageService.savePage(page, UserUIContext.getUsername(), CurrentProjectVariables.getProjectId(),
                AppUI.getAccountId());
    }
}
