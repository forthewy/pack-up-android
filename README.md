# Pack Up

여행, 출근, 운동 등 상황별 준비물을 관리할 수 있는 Android 체크리스트 앱입니다.
Flutter 프로젝트를 Android Native 기반으로, Room + Hilt + Flow를 활용하여 MVVM 아키텍처를 구현했습니다.



## 스크린샷

<img width="300" height="600" alt="2" src="https://github.com/user-attachments/assets/776ebbb0-1bd8-4c1e-b865-547f66a46f29" />

<img width="300" height="600" alt="1" src="https://github.com/user-attachments/assets/e5a09ca9-d1a6-4ec8-891f-f1cca52d2114" />


## 기능

- 카테고리별 준비물 관리
- 준비물 추가 / 수정 / 삭제
- 체크 상태 변경
- 진행률 표시
- 빈 목록일 때 추천 준비물 제공

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM
- Room
- Hilt
- Kotlin Coroutines
- Flow
- Navigation Compose


## 아키텍처
- ViewModel
- Repository
- Room Database

## UI Design

UI 디자인은 Stitch 의 도움을 받아 제작했습니다
다만 Stitch 는 html 코드로 전달해주기에 이를 수정해가며 작업했습니다.

## 다시 만든이유
해당 프로젝트는 기존 Flutter 프로젝트를 Android Native(Kotlin)로 다시 구현한것입니다.
Flutter 버전과 비교하여 데이터베이스, 의존성 주입, 상태 관리 등 전체 구조를 Android 개발 방식에 맞게 재설계했습니다.

### Flutter
- Hive
- l10n
- Dart

### Kotlin
- Room
- Hilt
- Flow
- Jetpack Compose

## 이후 개선 예정사항

- 검색 기능
- 추천 준비물 커스터마이징 (AI 활용)
- 다크 모드, 언어설정
- 백업 및 복원

- 
