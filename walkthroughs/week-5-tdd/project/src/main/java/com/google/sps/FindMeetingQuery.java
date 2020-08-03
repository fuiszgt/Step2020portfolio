// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.sql.Time;
import java.util.*;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    SortedSet<TimeRange> timeRangesSorted = new TreeSet<TimeRange>(TimeRange.ORDER_BY_START);
    for(Event e: events){ //some map could be used here
      if(hasAttendee(e, request.getAttendees())){
        timeRangesSorted.add(e.getWhen());
      }
    }

    TimeRange guardian = TimeRange.fromStartDuration(TimeRange.END_OF_DAY+1, 0); //Shouldn't timerange set outside the minutes of the day be invalid?
    timeRangesSorted.add(guardian); //Could be done by an additional if at the end as well

    int lastFinish = TimeRange.START_OF_DAY;
    Collection<TimeRange> appropriateTimeranges = new ArrayList<TimeRange>();
    for(TimeRange nextEvent: timeRangesSorted){
      if(lastFinish < nextEvent.start()){ //should I extract nextEvent.start() to a local variable?
        TimeRange empty = TimeRange.fromStartEnd(lastFinish, nextEvent.start(), false);
        if(empty.duration() >= request.getDuration()){
          appropriateTimeranges.add(empty);
        }
      }
      if(lastFinish < nextEvent.end()){
        lastFinish = nextEvent.end();
      }
    }

    return appropriateTimeranges;
  }

  public boolean hasAttendee(Event event, Collection<String> Attendees){//will it delete the attendees if the event?
    Collection<String> eventAttendees = new HashSet<String>();
    eventAttendees.addAll(event.getAttendees());
    eventAttendees.retainAll(Attendees);
    return eventAttendees.size() > 0;
  }
}
