package com.googlecode.wicket.jquery.ui.samples.pages.tabs;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.Model;

import com.googlecode.wicket.jquery.core.JQueryEvent;
import com.googlecode.wicket.jquery.ui.form.button.AjaxButton;
import com.googlecode.wicket.jquery.ui.samples.component.NavigationAjaxButton;
import com.googlecode.wicket.jquery.ui.samples.component.TabDialog;
import com.googlecode.wicket.jquery.ui.widget.tabs.AjaxTab;
import com.googlecode.wicket.jquery.ui.widget.tabs.SimpleTab;
import com.googlecode.wicket.jquery.ui.widget.tabs.TabbedPanel;

public class AdvancedTabsPage extends AbstractTabsPage
{
	private static final long serialVersionUID = 1L;

	private int tabIndex = 0;
	private final TabbedPanel tabPanel;

	public AdvancedTabsPage()
	{
		// Dialog //
		final TabDialog dialog = this.newTabDialog("dialog");
		this.add(dialog);

		// Form //
		Form<Void> form = new Form<Void>("form");
		this.add(form);

		// Nav-tab Buttons //
		final NavigationAjaxButton buttons = this.newNavigationAjaxButton("nav");
		form.add(buttons);

		// Add-tab Buttons //
		form.add(new AjaxButton("add") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				dialog.open(target);
			}
		});

		// TabbedPanel //
		this.tabPanel = new TabbedPanel("tabs", this.newTabList()) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onActivate(AjaxRequestTarget target, int index, ITab tab)
			{
				tabIndex = index;
				this.send(buttons, Broadcast.EXACT, new ChangeEvent(target));
			}
		};

		form.add(this.tabPanel);
	}

	// Factories //
	private TabDialog newTabDialog(String id)
	{
		return new TabDialog(id, "Add Tab") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onOpen(AjaxRequestTarget target)
			{
				super.onOpen(target);

				// Sets an empty model object //
				this.setModelObject(new TabItem());
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target)
			{
				// Adds the tab and re-attach the TabPanel //
				TabItem item = this.getModelObject();

				tabPanel.add(new SimpleTab(item.getTitle(), item.getContent()));
				target.add(tabPanel);
			}
		};
	}

	private NavigationAjaxButton newNavigationAjaxButton(String id)
	{
		return new NavigationAjaxButton(id) {

			private static final long serialVersionUID = 1L;
			private int max = 0;

			@Override
			protected void onConfigure()
			{
				super.onConfigure();

				this.max = this.count() - 1;

				this.getBackwardButton().setEnabled(tabIndex > 0);
				this.getForwardButton().setEnabled(tabIndex < this.max);
			}

			@Override
			protected void onBackward(AjaxRequestTarget target, AjaxButton button)
			{
				if (tabIndex > 0)
				{
					tabPanel.setActiveTab(tabIndex - 1, target); // fires onActivate
				}
			}

			@Override
			protected void onForward(AjaxRequestTarget target, AjaxButton button)
			{
				if (tabIndex < this.max)
				{
					tabPanel.setActiveTab(tabIndex + 1, target);
				}
			}

			@Override
			public void onEvent(IEvent<?> event)
			{
				super.onEvent(event);

				// broadcasted by the TabbedPanel
				if (event.getPayload() instanceof ChangeEvent)
				{
					ChangeEvent payload = (ChangeEvent) event.getPayload();
					AjaxRequestTarget target = payload.getTarget();

					target.add(this);
				}
			}

			/**
			 * Gets count of visible tabs
			 *
			 * @return the count
			 */
			private int count()
			{
				int count = 0;

				for (ITab tab : tabPanel.getModelObject())
				{
					if (tab.isVisible())
					{
						count++;
					}
				}

				return count;
			}
		};
	}

	private List<ITab> newTabList()
	{
		List<ITab> tabs = new ArrayList<ITab>();

		// tab #1, using SimpleTab //
		tabs.add(new SimpleTab(Model.of("Tab #1"), Model.of("my content")));

		// tab #2, invisible Tab //
		tabs.add(new SimpleTab(Model.of("Tab #2"), Model.of("invisible")) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible()
			{
				return false;
			}
		});

		// tab #3, using AbstractTab //
		tabs.add(new AbstractTab(Model.of("Tab #3")) {

			private static final long serialVersionUID = 1L;

			@Override
			public WebMarkupContainer getPanel(String panelId)
			{
				return new Fragment(panelId, "panel-1", AdvancedTabsPage.this);
			}
		});

		// tab #4, using AjaxTab //
		tabs.add(new AjaxTab(Model.of("Tab (ajax)")) {

			private static final long serialVersionUID = 1L;

			@Override
			public WebMarkupContainer getLazyPanel(String panelId)
			{
				try
				{
					// sleep the thread for a half second to simulate a long load
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					error(e.getMessage());
				}

				return new Fragment(panelId, "panel-2", AdvancedTabsPage.this);
			}
		});

		return tabs;
	}

	static class ChangeEvent extends JQueryEvent
	{
		private final AjaxRequestTarget target;

		public ChangeEvent(AjaxRequestTarget target)
		{
			this.target = target;
		}

		public AjaxRequestTarget getTarget()
		{
			return this.target;
		}
	}
}
