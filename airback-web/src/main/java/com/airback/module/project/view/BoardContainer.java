package com.airback.module.project.view;

import com.airback.common.i18n.ClientI18nEnum;
import com.airback.common.i18n.GenericI18Enum;
import com.airback.configuration.SiteConfiguration;
import com.airback.module.project.ProjectTypeConstants;
import com.airback.module.project.i18n.ProjectCommonI18nEnum;
import com.airback.module.project.i18n.ProjectI18nEnum;
import com.airback.module.project.ui.ProjectAssetsManager;
import com.airback.module.project.view.client.IClientPresenter;
import com.airback.module.project.view.parameters.ClientScreenData;
import com.airback.module.project.view.reports.IReportPresenter;
import com.airback.vaadin.UserUIContext;
import com.airback.vaadin.mvp.AbstractSingleContainerPageView;
import com.airback.vaadin.mvp.PageView;
import com.airback.vaadin.mvp.PresenterResolver;
import com.airback.vaadin.mvp.ViewComponent;
import com.airback.vaadin.web.ui.VerticalTabsheet;
import com.airback.vaadin.web.ui.VerticalTabsheet.ButtonTab;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import org.vaadin.viritin.layouts.MCssLayout;

/**
 * @author airback Ltd
 * @since 7.0.0
 */
@ViewComponent
public class BoardContainer extends AbstractSingleContainerPageView implements PageView {

    private VerticalTabsheet tabsheet;

    public BoardContainer() {
        tabsheet = new VerticalTabsheet();
        setContent(tabsheet);
        MCssLayout contentWrapper = tabsheet.getContentWrapper();
        contentWrapper.withStyleName("content-height");
        tabsheet.addToggleNavigatorControl();
        this.buildComponents();
    }

    public CssLayout getContentWrapper() {
        return tabsheet.getContentWrapper();
    }

    private void buildComponents() {
        tabsheet.addTab("Dashboard", UserUIContext.getMessage(GenericI18Enum.VIEW_DASHBOARD), VaadinIcons.DASHBOARD);
        tabsheet.addTab("Projects", UserUIContext.getMessage(ProjectI18nEnum.LIST), ProjectAssetsManager.getAsset(ProjectTypeConstants.PROJECT));
        tabsheet.addTab("Reports", UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_REPORTS), VaadinIcons.RETWEET);

        if (!SiteConfiguration.isCommunityEdition()) {
            tabsheet.addTab("Clients", UserUIContext.getMessage(ClientI18nEnum.LIST), VaadinIcons.COIN_PILES);
        }

        tabsheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                ButtonTab tab = ((VerticalTabsheet) event.getSource()).getSelectedTab();
                String tabId = tab.getTabId();
                if ("Dashboard".equals(tabId)) {
                    UserProjectDashboardPresenter presenter = PresenterResolver.getPresenter(UserProjectDashboardPresenter.class);
                    presenter.go(BoardContainer.this, null);
                } else if ("Projects".equals(tabId)) {
                    ProjectListPresenter presenter = PresenterResolver.getPresenter(ProjectListPresenter.class);
                    presenter.go(BoardContainer.this, null);
                } else if ("Reports".equals(tabId)) {
                    IReportPresenter presenter = PresenterResolver.getPresenter(IReportPresenter.class);
                    presenter.go(BoardContainer.this, null);
                } else if ("Clients".equals(tabId)) {
                    IClientPresenter presenter = PresenterResolver.getPresenter(IClientPresenter.class);
                    presenter.go(BoardContainer.this, new ClientScreenData.Search(null));
                }
            }
        });
    }

    public void gotoSubView(String viewId, Component viewDisplay) {
        tabsheet.selectTab(viewId, viewDisplay);
    }
}
