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
package com.airback.module.project.view.user;

import com.airback.module.project.CurrentProjectVariables;
import com.airback.module.project.ProjectRolePermissionCollections;
import com.airback.module.project.ProjectTypeConstants;
import com.airback.module.project.view.*;
import com.airback.module.project.view.parameters.ProjectScreenData;
import com.airback.module.project.view.parameters.ReportScreenData;
import com.airback.module.project.view.parameters.StandupScreenData;
import com.airback.module.project.view.reports.IReportPresenter;
import com.airback.vaadin.mvp.*;
import com.airback.vaadin.ui.NotificationUtil;
import com.airback.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author airback Ltd.
 * @since 1.0
 */
public class ProjectDashboardPresenter extends AbstractPresenter<ProjectDashboardContainer> {
    private static final long serialVersionUID = 1L;

    public ProjectDashboardPresenter() {
        super(ProjectDashboardContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectView projectView = (ProjectView) container;
        projectView.gotoSubView(ProjectView.SUMMARY_ENTRY, view);

        if (data instanceof ProjectScreenData.Edit) {
            if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.PROJECT)) {
                ProjectAddPresenter presenter = PresenterResolver.getPresenter(ProjectAddPresenter.class);
                presenter.go(view, data);
            } else {
                NotificationUtil.showMessagePermissionAlert();
            }
        } else if (data instanceof ProjectScreenData.GotoTagList) {
            ITagListPresenter presenter = PresenterResolver.getPresenter(ITagListPresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ProjectScreenData.GotoFavorite) {
            IFavoritePresenter presenter = PresenterResolver.getPresenter(IFavoritePresenter.class);
            presenter.go(view, data);
        } else if (data instanceof ProjectScreenData.GotoReportConsole || data instanceof StandupScreenData.Search
                || data instanceof ReportScreenData.GotoWeeklyTiming) {
            IReportPresenter presenter = PresenterResolver.getPresenter(IReportPresenter.class);
            presenter.go(view, data);
        } else {
            if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.PROJECT)) {
                ProjectSummaryPresenter presenter = PresenterResolver.getPresenter(ProjectSummaryPresenter.class);
                presenter.go(view, data);
            } else {
                NotificationUtil.showMessagePermissionAlert();
            }
        }
    }

    @Override
    protected void onHandleChain(HasComponents container, PageActionChain pageActionChain) {
        ScreenData<?> pageAction = pageActionChain.peek();

        Class<? extends IPresenter> presenterCls = ProjectPresenterDataMapper.presenter(pageAction);
        if (presenterCls != null) {
            IPresenter<?> presenter = PresenterResolver.getPresenter(presenterCls);
            presenter.handleChain(view, pageActionChain);
        } else {
            throw new UnsupportedOperationException("Not support page action chain " + pageAction);
        }
    }
}
