/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 12/12/15.
 */
(function () {
    'use strict';

    angular.module('corestudioApp.group')
        .controller('GroupModalController', GroupModalController);

    GroupModalController.$inject = ['$uibModalInstance', 'group', 'config', 'Activity', '$scope'];

    function GroupModalController($uibModalInstance, group, config, Activity, $scope) {
        var vm = this;
        vm.days = config.days;
        vm.levels = config.levels;
        vm.days = config.days;
        vm.selectedGroup = group || {days: []};
        var isNew = group == undefined;
        vm.title = isNew ? 'Crear grupo' : 'Editar grupo';
        vm.isDaysEmpty = false;
        vm.dismiss = dismiss;
        vm.savePassword = savePassword;
        var listener;

        activate();

        /////////////////////

        function activate() {
            Activity.getGroupActivities({}, function(data) {
                vm.activities = data;
            });
        }

        function dismiss() {
            $uibModalInstance.dismiss('CANCEL');
        }

        function savePassword() {
            $scope.$broadcast('show-errors-check-validity');

            if ($scope.groupForm.$valid && vm.selectedGroup.days.length > 0) {
                if(isNew) {
                    $uibModalInstance.close({action: 'SAVE', group: vm.selectedGroup});
                } else {
                    $uibModalInstance.close({action: 'UPDATE', group: vm.selectedGroup});
                }
            } else if(vm.selectedGroup.days.length === 0) {
                if(listener === undefined) {
                    listener = $scope.$watch(function () {
                        return vm.selectedGroup.days.length;
                    }, function() {
                        vm.isDaysEmpty = vm.selectedGroup.days.length === 0;
                    });
                }
            }
        }
    }

})();

