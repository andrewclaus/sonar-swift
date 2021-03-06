/**
 * Swift SonarQube Plugin - Swift module - Enables analysis of Swift and Objective-C projects into SonarQube.
 * Copyright © 2015 Backelite (${email})
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.backelite.sonarqube.swift.issues.tailor;

import com.backelite.sonarqube.swift.lang.core.Swift;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.profiles.ProfileImporter;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.profiles.XMLProfileParser;
import org.sonar.api.utils.ValidationMessages;

import java.io.Reader;

/**
 * Created by tzwickl on 22/11/2016.
 */

public class TailorProfileImporter extends ProfileImporter {

    private static final String UNABLE_TO_LOAD_DEFAULT_PROFILE = "Unable to load default tailor profile";
    private static final Logger LOGGER = LoggerFactory.getLogger(TailorProfileImporter.class);

    private final XMLProfileParser profileParser;

    public TailorProfileImporter(final XMLProfileParser xmlProfileParser) {
        super(TailorRulesDefinition.REPOSITORY_KEY, TailorRulesDefinition.REPOSITORY_KEY);
        setSupportedLanguages(Swift.KEY);
        this.profileParser = xmlProfileParser;
    }

    @Override
    public RulesProfile importProfile(final Reader reader, final ValidationMessages messages) {

        final RulesProfile profile = this.profileParser.parse(reader, messages);

        if (null == profile) {
            messages.addErrorText(UNABLE_TO_LOAD_DEFAULT_PROFILE);
            LOGGER.error(UNABLE_TO_LOAD_DEFAULT_PROFILE);
        }

        return profile;
    }
}
