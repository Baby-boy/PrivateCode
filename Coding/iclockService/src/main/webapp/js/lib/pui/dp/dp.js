
function getTime(calendar) {
    var $calendar = $(calendar),
    // time = $calendar.find('.checked[data-time]').data('time'),
        hour = $calendar.find('input.time-hour').val(),
        minute = $calendar.find('input.time-minute').val(),
        second = $calendar.find('input.time-second').val();
    return 1000 * 3600 * hour + 1000 * 60 * minute + 1000 * second;
}
var INSTANCES = {

};
var MONTH = { 0: '一月', 1: '二月', 2: '三月', 3: '四月', 4: '五月', 5: '六月', 6: '七月', 7: '八月', 8: '九月', 9: '十月', 10: '十一月', 11: '十二月' };
function getValue(calendar) {
    var $calendar = $(calendar),
        $checked = $calendar.find('.checked[data-time]'),
        min = $checked.first().data('time'),
        max = $checked.last().data('time');

    return 0 < $checked.length ? [Math.min(min, max), Math.max(min, max)] : [];
}
function setDate(calendar, date) {
    var $calendar = $(calendar),
        $beforeCalendar = $calendar.prevUntil('', '.calendar').first(),
        $afterCalendar = $calendar.nextUntil('', '.calendar').first(),
        time;

    date = date || new Date();
    date = new Date(date.getFullYear(), date.getMonth(), 1);
    time = date.getTime();

    $calendar.find('.month-name').text(MONTH[date.getMonth()] + ' ' + date.getFullYear());
    $calendar.find('.month-bg').text(date.getMonth() + 1);
    $calendar.find('tbody').empty().html(buildMonth(date));

    if (0 < $beforeCalendar.length) {
        var prevMonth = new Date(date.getFullYear(), date.getMonth() - 1);
        var beforeCalendarTime = +$beforeCalendar.find('.calendar-day:not(.calendar-day-phantom)').first().attr('data-time');
        // 如果前面的 calendar 与当前 calendar 月份相同
        if (isNaN(beforeCalendarTime) || beforeCalendarTime > prevMonth.getTime()) {
            setDate($beforeCalendar, prevMonth);
            $calendar.prevUntil('', '.gap').first().removeClass('crack');
        } else if (beforeCalendarTime == prevMonth.getTime()) {
            $calendar.prevUntil('', '.gap').first().removeClass('crack');
        } else {
            $calendar.prevUntil('', '.gap').first().addClass('crack');
        }
    }
    if (0 < $afterCalendar.length) {
        var nextMonth = new Date(date.getFullYear(), date.getMonth() + 1);
        var afterCalendarTime = +$afterCalendar.find('.calendar-day:not(.calendar-day-phantom)').first().attr('data-time');
        if (isNaN(afterCalendarTime) || afterCalendarTime < nextMonth.getTime()) {
            setDate($afterCalendar, nextMonth);
            $calendar.nextUntil('', '.gap').first().removeClass('crack');
        } else if (afterCalendarTime == nextMonth.getTime()) {
            $calendar.nextUntil('', '.gap').first().removeClass('crack');
        } else {
            $calendar.nextUntil('', '.gap').first().addClass('crack');
        }
    }
}

function addMonth(calendar, num) {
    var $calendar = $(calendar),
        time = +$calendar.find('.calendar-day:not(.calendar-day-phantom)').first().attr('data-time'),
        month = new Date(time);
    num = null == num ? 1 : num;
    setDate(calendar, new Date(month.getFullYear(), month.getMonth() + num));
}

$(document).on('focus', 'input.j_Calendar', function() {
    init(this).addClass('date-picker-container-with-drop');
}).on('click', '.drop-ok', function() {
        var $c = $('.calendar').first(),
            time = getTime($c),
            d = new Date(time);
        console.log(d);
}).on('click', '.calendar-prev', function() {
    var $self = $(this),
        $calendar = $self.closest('.calendar');
    addMonth($calendar, -1);
}).on('click', '.calendar-next', function() {
    var $self = $(this),
        $calendar = $self.closest('.calendar');
    addMonth($calendar, 1);
}).on('click', '.month-name', function() {
    $(this).closest('.calendar').find('.calender-selector').css('visibility', 'visible');
}).on('click', '.calendar-day', function() {
    var $self = $(this);
    if ($self.is('.calendar-day-phantom')) {
        return;
    }

    var range = 0;
    if (!range) {
        $self.closest('.date-picker-container').removeClass('date-picker-container-with-drop');
        $self.addClass('checked').closest('.calendar-view').find('.calendar-day.checked').not($self).removeClass('checked');

        var time = getValue($self.closest('.calendar'))[0] + getTime($self.closest('.calendar')),
            date = new Date(time);
        $('#h').val(date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ' ' + date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds());
        return;
    }

    var $checked = $self.closest('.calendar-view').find('td.checked');
    if (1 != $checked.length) {
        $checked.removeClass('checked');
        // $self.toggleClass('checked');
        $('.calendar-day[data-time=' + $self.data('time') + ']').toggleClass('checked');
    } else {
        var checkedTime = +$checked.attr('data-time');
        var time = $self.attr('data-time');
        if (time < checkedTime) {
            $checked.removeClass('checked');
            $self.toggleClass('checked');
//                                    $('.calendar-day[data-time=' + $self.data('time') + ']').toggleClass('checked');
        } else {
            // nextUtil 不好使
            var $tds = $self.closest('.calendar-view').find('td');
            var s = 0;
            $tds.each(function(i, item) {
                if (item == $self[0]) {
                    return false;
                } else if (item == $checked[0]) {
                    s = 1;
                } else if (s) {
                    $(item).addClass('checked');
                }
            });
            $self.toggleClass('checked');
//                                    $('.calendar-day[data-time=' + $self.data('time') + ']').toggleClass('checked');
        }
    }
}).on('focus', '.time input', function() {
    var me = this;
    me.setSelectionRange && me.setSelectionRange(0, me.value.length);
}).on('keydown', '.time input', function(evt) {
    var me = this, value = me.value, max = $(me).hasClass('time-hour') ? 23 : 59, min = 0;
    // left or down
    if (37 == evt.keyCode || 40 == evt.keyCode) {
        value = +value - 1;
        value = value > max ? min : value;
        value = value < min ? max : value;
        value = value < 10 ? '0' + value : value;
        me.value = value;
        me.setSelectionRange && me.setSelectionRange(0, me.value.length);
        evt.stopPropagation();
        return false;
    } else if (38 == evt.keyCode || 40 == evt.keyCode) {    // right or down
        value = +value + 1;
        value = value > max ? min : value;
        value = value < min ? max : value;
        value = value < 10 ? '0' + value : value;
        me.value = value;
        me.setSelectionRange && me.setSelectionRange(0, me.value.length);
        evt.stopPropagation();
        return false;
    }
});

var option = {
    max: new Date(),
    min: new Date()
};

var SUNDAY = 0;
function buildMonth(date) {
    var html = '', week;

    date = new Date(date.getFullYear(), date.getMonth(), 1);
    week = date.getDay();

    var day = 0;

    html += '<tr>';

    // 第一天不是周末
    if (SUNDAY != week) {
        date.setDate(-(week - 1));   // 上个月最后一个周末
        for(var i = 0; i < week; i++) {
            html += '<td class="calendar-day calendar-day-phantom" data-time="' + date.getTime() + '">' + date.getDate() + '</td>';
            date.setDate(date.getDate() + 1);
            day++;
        }
    }

    do {
        html += '<td class="calendar-day" data-time="' + date.getTime() + '">' + date.getDate() + '</td>';
        date.setDate(date.getDate() + 1);
        day++;
        if (0 == day % 7) {
            html += '</tr>';
            html += '<tr>';
        }
    } while(1 != date.getDate());

    // 不是周六
    week = date.getDay();
    if (week != 6) {
        week = date.getDay();

        for(;0 != week && week <= 6; week++) {
            html += '<td class="calendar-day calendar-day-phantom" data-time="' + date.getTime() + '">' + date.getDate() + '</td>';
            date.setDate(date.getDate() + 1);
        }
    }
    html += '</tr>';
    return html;
}

var DEFAULT = {
    el: undefined,
    range: true,
    calendars: 1,
    time: true,
    format: 'yyyy-MM-dd',
    min: -Infinity,
    max: Infinity,
    callback: $.noop
};

var CALENDAR = '' +
    '<div class="calendar">' +
    '   <i class="month-bg">11</i>'+
    '   <table class="month1" cellspacing="0" border="0" cellpadding="0">'+
    '       <thead>'+
    '       <tr class="calendar-menu">'+
    '           <th class="calendar-prev"><span>&lsaquo;</span></th>'+
    '           <th colspan="5" class="month-name">october 2015</th>'+
    '           <th class="calendar-next"><span>&rsaquo;</span></th>'+
    '       </tr>' +
    '       <tr class="calendar-weeks">' +
    '           <th>Su</th>' +
    '           <th>Mo</th>' +
    '           <th>Tu</th>' +
    '           <th>We</th>' +
    '           <th>Th</th>' +
    '           <th>Fr</th>' +
    '           <th>Sa</th>' +
    '       </tr>' +
    '       </thead>' +
    '       <tbody></tbody>' +
    '    </table>' +
    '    <div class="time" style="float:left;">' +
    '       <span class="time-text">时间</span>' +
    '       <input class="time-hour" type="text" value="23"><i>:</i>' +
    '       <input class="time-minute" type="text" value="22"><i>:</i>' +
    '       <input class="time-second" type="text" value="11">' +
    '   </div>' +
    '    <!--<a href="" style="float:right;">现在</a>-->' +
    '</div>';

function init(input) {
    if (INSTANCES[input]) {
        return INSTANCES[input];
    }
    var $container = $('.date-picker-container');
    $container.prepend($(input));
    var $view = $container.find('.calendar-view');
    $view.empty();

    for(var i = 0; i < 3; i++) {
        var $calendar = $(CALENDAR),
            $tbody = $calendar.find('tbody');
        $tbody.html(buildMonth(new Date()));
        $calendar.appendTo($view);
    }

    setDate($view.find('.calendar:eq(0)'), new Date());
    return INSTANCES[input] = $container;
}
function destroy(input) {
    if (INSTANCES[input]) {
        INSTANCES[input].before(input);
        INSTANCES[input].remove();
        INSTANCES[input] = null;
        delete INSTANCES[input];
    }
}

/*
 $('.date-picker-container').click(function() {
 $(this).toggleClass('date-picker-container-with-drop')
 })
 */
