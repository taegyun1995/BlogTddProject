그림부터 설계

블로그 저장을 만든다고 하면
블로그 저장할 때, 블로그라고 하는 도메인이 어떤 상태가 있는가? 를 먼저 고민
맨 처음에는 블로그가 생성됨이라는 상태가 있고 그 다음에 삭제라는 오퍼레이션이 생기면 생성에서 삭제됨 이라는 상태가 필요..

수정이라는 오퍼레이션이 있다면.. 이 수정은 상태가 바뀐게 없다..그래서 할게 없다..

삭제라는 오퍼레이션은 생성된 상태에서만 가능,, 수정도 생성된 상태에서만 가능

저장은 아무것도 없는 상태에서만 가능

오퍼레이션 행위들은 전부 어플리케이션 이벤트.

도메인 이벤트는 각각의 도메인들이 상태가 바꼇을 때만

그 후에 테스트코드를 구현
블로그 삭제는 '생성됨' 상태에서만 가능하다.
var sut = new Blog();
sut.delete();

블로그를 처음 생성하면 상태는 생성됨이다.

수정은 '생성됨' 상태에서만 가능하다.
<sut = new Blog();
sut.delete();> 생성된 상태에서 삭제 이벤트 날리면.. ㅠㅠ
sut.modifY();를 날렸는데 삭제된 상태에서는 안된다는 Exception이 발생해야 한다는 단언문이 필요

추후에 좋아요 기능이 생긴다고 하면..
거기에 룰을 추가..ㅠ-ㅠ