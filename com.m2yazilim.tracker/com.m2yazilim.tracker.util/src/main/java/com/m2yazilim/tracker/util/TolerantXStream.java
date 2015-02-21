package com.m2yazilim.tracker.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

//This is to omits tags that we are not interested in

public class TolerantXStream extends XStream {
	@Override
	protected MapperWrapper wrapMapper(MapperWrapper next) {
		return new MapperWrapper(next) {
			public boolean shouldSerializeMember(Class definedIn,
					String fieldName) {
				return definedIn != Object.class ? super.shouldSerializeMember(
						definedIn, fieldName) : false;
			}

		};
	}
}