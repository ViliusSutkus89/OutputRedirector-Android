# Copyright (C) 2024
# ViliusSutkus89.com
#
# OutputRedirector-Android is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License version 3,
# as published by the Free Software Foundation.
#
# OutputRedirector-Android is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <https://www.gnu.org/licenses/>.

import os.path
import pathlib
import shutil


class Versioner:
    def __init__(self):
        here = pathlib.Path(__file__).parent.resolve()
        self.version_file = os.path.abspath(here.parent / 'outputredirector' / 'build.gradle.kts')

        self.version_name = None

        self.__parse_versions()

    def __parse_versions(self):
        with open(self.version_file) as f:
            for line in f:
                if line.startswith('version'):
                    separator = '"' if '"' in line else "'"
                    splits = line.split(separator)
                    self.version_name = splits[1]
                    return

    def __save_version(self):
        tmp_file = f'{self.version_file}.tmp'
        with open(self.version_file) as fread:
            with open(tmp_file, 'w') as fwrite:
                for line in fread:
                    if line.startswith('version'):
                        separator = '"' if '"' in line else "'"
                        splits = line.split(separator)
                        splits[1] = self.version_name
                        line = separator.join(splits)
                    fwrite.write(line)
        shutil.move(tmp_file, self.version_file)

    def increment_major(self):
        version_name = self.version_name.split('.')
        version_name = (str(int(version_name[0]) + 1), '0', '0')
        self.version_name = '.'.join(version_name)
        self.__save_version()

    def increment_minor(self):
        version_name = self.version_name.split('.')
        version_name = (version_name[0], str(int(version_name[1]) + 1), '0')
        self.version_name = '.'.join(version_name)
        self.__save_version()

    def increment_patch(self):
        version_name = self.version_name.split('.')
        version_name[2] = str(int(version_name[2]) + 1)
        self.version_name = '.'.join(version_name)
        self.__save_version()
