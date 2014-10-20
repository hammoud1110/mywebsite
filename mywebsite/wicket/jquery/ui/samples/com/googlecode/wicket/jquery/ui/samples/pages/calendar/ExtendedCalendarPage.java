package com.googlecode.wicket.jquery.ui.samples.pages.calendar;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.calendar.Calendar;
import com.googlecode.wicket.jquery.ui.calendar.CalendarView;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;
import com.googlecode.wicket.jquery.ui.samples.component.DemoCalendarDialog;
import com.googlecode.wicket.jquery.ui.samples.data.DemoCalendarEvent;
import com.googlecode.wicket.jquery.ui.samples.data.DemoCalendarModel;
import com.googlecode.wicket.jquery.ui.samples.data.dao.CalendarDAO;

public class ExtendedCalendarPage extends AbstractCalendarPage
{
	private static final long serialVersionUID = 1L;

	private Calendar calendar;

	public ExtendedCalendarPage()
	{
		// Form //
		final Form<Date> form = new Form<Date>("form");
		this.add(form);

		// FeedbackPanel //
		final FeedbackPanel feedback = new JQueryFeedbackPanel("feedback");
		form.add(feedback.setOutputMarkupId(true));

		// Dialog //
		final DemoCalendarDialog dialog = new DemoCalendarDialog("dialog", "Event details") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target)
			{
				DemoCalendarEvent event = this.getModelObject();

				// new event //
				if (CalendarDAO.isNew(event))
				{
					CalendarDAO.addEvent(event);
				}

				calendar.refresh(target); //use calendar.refresh(target) instead of target.add(calendar)
			}
		};

		this.add(dialog);

		// Calendar //
		Options options = new Options();
		options.set("theme", true);
		options.set("header", "{ left: 'title', right: 'month,agendaWeek,agendaDay, today, prev,next' }");

		this.calendar = new Calendar("calendar", new DemoCalendarModel(), options) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isSelectable()
			{
				return true;
			}

			@Override
			public boolean isDayClickEnabled()
			{
				return true;
			}

			@Override
			public boolean isEventClickEnabled()
			{
				return true;
			}

			@Override
			public boolean isEventDropEnabled()
			{
				return true;
			}

			@Override
			public boolean isEventResizeEnabled()
			{
				return true;
			}

			@Override
			public void onDayClick(AjaxRequestTarget target, CalendarView view, Date date, boolean allDay)
			{
				DemoCalendarEvent event = CalendarDAO.newEvent(date);

				dialog.setModelObject(event);
				dialog.open(target);
			}

			@Override
			public void onSelect(AjaxRequestTarget target, CalendarView view, Date start, Date end, boolean allDay)
			{
				DemoCalendarEvent event = CalendarDAO.newEvent(start, end);
				event.setAllDay(allDay);

				dialog.setModelObject(event);
				dialog.open(target);
			}

			@Override
			public void onEventClick(AjaxRequestTarget target, CalendarView view, int eventId)
			{
				DemoCalendarEvent event = CalendarDAO.getEvent(eventId);

				if (event != null)
				{
					dialog.setModelObject(event);
					dialog.open(target);
				}
			}

			@Override
			public void onEventDrop(AjaxRequestTarget target, int eventId, long delta, boolean allDay)
			{
				DemoCalendarEvent event = CalendarDAO.getEvent(eventId);

				if (event != null)
				{
					event.setStart(event.getStart() != null ? new Date(event.getStart().getTime() + delta) : null);	//recompute start date
					event.setEnd(event.getEnd() != null ? new Date(event.getEnd().getTime() + delta) : null);	// recompute end date
					event.setAllDay(allDay);

					this.info(String.format("%s changed to %s", event.getTitle(), event.getStart()));
					target.add(feedback);
				}
			}

			@Override
			public void onEventResize(AjaxRequestTarget target, int eventId, long delta)
			{
				DemoCalendarEvent event = CalendarDAO.getEvent(eventId);

				if (event != null)
				{
					Date date = event.getEnd() == null ? event.getStart() : event.getEnd();
					event.setEnd(new Date(date.getTime() + delta));

					this.info(String.format("%s now ends the %s", event.getTitle(), event.getEnd()));
					target.add(feedback);
				}
			}
		};

		form.add(this.calendar);
	}
}
